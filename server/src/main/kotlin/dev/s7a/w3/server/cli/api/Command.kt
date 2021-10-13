package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.ErrorCode
import org.jline.builtins.Completers.TreeCompleter
import org.jline.reader.Completer
import org.jline.reader.EndOfFileException
import org.jline.reader.LineReader
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.DefaultParser
import kotlin.system.exitProcess

/**
 * コマンド
 * @param parent 親コマンド
 * @param name コマンド名
 * @param description 説明文
 */
sealed class Command(
    protected open val parent: Container?,
    val name: String,
    protected val description: String,
) {
    /**
     * 実行名
     */
    val runName: String
        get() = "${parent?.let { "${it.runName} " } ?: ""}$name"

    /**
     * ヘルプメッセージ
     */
    open fun getHelpMessage(platform: ExecutionPlatform) = "$name -> $description"

    /**
     * コマンドを処理する
     * @param platform 実行環境
     * @param args 実行引数
     * @param index 引数のインデックス
     */
    abstract fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int)

    /**
     * サブコマンドを持つコマンド
     */
    class Container(parent: Container?, name: String, description: String) : Command(parent, name, description) {
        private val _commands = mutableListOf<Command>()

        /**
         * ヘルプメッセージを表示するコマンド
         */
        private val helpMessageCommand = Action.Help(this)

        /**
         * サブコマンド
         */
        val commands
            get() = _commands.toList() + helpMessageCommand

        /**
         * 単純な処理を実行するコマンドを追加する
         * @param name コマンド名
         * @param description 説明文
         * @param action 実行する処理
         */
        fun register(name: String, description: String, action: ExecutionPlatform.() -> Unit) {
            _commands.add(Action.Simple(this, name, description, action))
        }

        /**
         * 引数を必要とする処理を実行するコマンドを追加する
         * @param name コマンド名
         * @param optionName オプション名
         * @param description 説明文
         * @param action 実行する処理
         */
        fun register(name: String, optionName: String, description: String, action: ExecutionPlatform.(String?) -> Unit) {
            _commands.add(Action.RequireOption(this, name, description, optionName, action))
        }

        /**
         * 対話モードを開始するコマンドを追加する
         * @param name コマンド名
         * @param exitName 対話モードを終了するときのコマンド名
         * @param description 説明文
         */
        fun interact(name: String, exitName: String, description: String) {
            _commands.add(Action.Interact(this, name, exitName, description))
        }

        /**
         * サブコマンドを持つコマンドを追加する
         * @param name コマンド名
         * @param description 説明文
         * @param action サブコマンドを追加する処理
         */
        fun container(name: String, description: String, action: Container.() -> Unit) {
            _commands.add(Container(this, name, description).apply(action))
        }

        override fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int) {
            val commandName = args.getOrNull(index)
            val command = commands.firstOrNull { it.name.equals(commandName, true) }
            if (command != null) {
                command.execute(platform, args, index + 1)
            } else {
                helpMessageCommand.execute(platform, args, index)
                if (commandName != null) {
                    platform.printError(ErrorCode.NotFoundCommand, "コマンドが見つかりませんでした")
                }
            }
        }
    }

    /**
     * 処理を実行するコマンド
     */
    sealed class Action(override val parent: Container, name: String, description: String) : Command(parent, name, description) {
        /**
         * 単純な処理
         */
        class Simple(parent: Container, name: String, description: String, val action: ExecutionPlatform.() -> Unit) : Action(parent, name, description) {
            override fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int) {
                platform.action()
            }
        }

        /**
         * 引数を必要とする処理
         */
        class RequireOption(parent: Container, name: String, description: String, private val optionName: String, val action: ExecutionPlatform.(String?) -> Unit) : Action(parent, name, description) {
            override fun getHelpMessage(platform: ExecutionPlatform) = "$name <$optionName> -> $description"

            override fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int) {
                platform.action(args.getOrNull(index))
            }
        }

        /**
         * 対話モード
         */
        class Interact(parent: Container, name: String, private val exitName: String, description: String) : Action(parent, name, description) {
            override fun getHelpMessage(platform: ExecutionPlatform): String {
                return if (platform is ExecutionPlatform.Interact) {
                    "$exitName -> 対話モードを終了する"
                } else {
                    super.getHelpMessage(platform)
                }
            }

            /**
             * タブ補完
             */
            private fun getCompleter(): Completer {
                fun Container.getNodeList(): Array<TreeCompleter.Node> {
                    return commands.map {
                        when (it) {
                            is Container -> {
                                TreeCompleter.node(it.name, *it.getNodeList())
                            }
                            is Interact -> {
                                TreeCompleter.node(exitName)
                            }
                            else -> {
                                TreeCompleter.node(it.name)
                            }
                        }
                    }.toTypedArray()
                }
                return TreeCompleter(*parent.getNodeList())
            }

            /**
             * 対話モードのラインリーダーを取得する
             */
            private fun getLineReader(): LineReader {
                val parser = DefaultParser()
                val completer = getCompleter()
                return LineReaderBuilder.builder()
                    .parser(parser)
                    .completer(completer)
                    .build()
            }

            override fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int) {
                if (platform is ExecutionPlatform.Command) {
                    println("対話モードを開始します")
                    val lineReader = getLineReader()
                    while (true) {
                        val input = try {
                            lineReader.readLine(">> ").trim()
                        } catch (ex: UserInterruptException) {
                            return
                        } catch (ex: EndOfFileException) {
                            return
                        }
                        if (input.isEmpty()) continue
                        when (input) {
                            exitName -> {
                                exitProcess(0)
                            }
                            else -> {
                                parent.execute(ExecutionPlatform.Interact, input.split("\\s+".toRegex()).toTypedArray(), 0)
                            }
                        }
                    }
                } else {
                    platform.printError(ErrorCode.PlatformAlreadyInteract, "既に対話モードです")
                }
            }
        }

        /**
         * ヘルプメッセージ
         */
        class Help(parent: Container) : Action(parent, "help", "コマンドヘルプを表示します") {
            override fun execute(platform: ExecutionPlatform, args: Array<String>, index: Int) {
                if (platform is ExecutionPlatform.Command) {
                    println("Usage: ${parent.runName} <Command>")
                }
                println("Commands:")
                parent.commands.forEach {
                    println("    ${it.getHelpMessage(platform)}")
                }
                if (platform is ExecutionPlatform.Command) {
                    println("Flags:")
                    Flag.list.forEach {
                        println("    ${it.fullName}, ${it.shortName} -> ${it.description}")
                    }
                }
            }
        }
    }

    companion object {
        /**
         * コマンドを定義する
         */
        fun registerCommand(programName: String, action: Container.() -> Unit) = Container(null, programName, "").apply(action)
    }
}
