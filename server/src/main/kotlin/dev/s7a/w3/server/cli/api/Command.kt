package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.ErrorCodes
import dev.s7a.w3.server.cli.util.printError

/**
 * コマンド
 * @param parent 親コマンド
 * @param name コマンド名
 * @param description 説明文
 */
sealed class Command(
    private val parent: Container?,
    val name: String,
    description: String,
) {
    /**
     * 実行名
     */
    val runName: String
        get() = "${if (parent != null) "${parent.runName} " else ""}$name"

    /**
     * ヘルプメッセージ
     */
    open val helpMessage = "$name -> $description"

    /**
     * コマンドを処理する
     * @param args 実行引数
     * @param index 引数のインデックス
     */
    abstract fun execute(args: Array<String>, index: Int)

    /**
     * サブコマンドを持つコマンド
     */
    class Container(parent: Container?, name: String, description: String) : Command(parent, name, description) {
        /**
         * サブコマンド
         */
        private val commands = mutableListOf<Command>()

        /**
         * 単純な処理を実行するコマンドを追加する
         * @param name コマンド名
         * @param description 説明文
         * @param action 実行する処理
         */
        fun register(name: String, description: String, action: () -> Unit) {
            commands.add(Action.Simple(this, name, description, action))
        }

        /**
         * 引数を必要とする処理を実行するコマンドを追加する
         * @param name コマンド名
         * @param optionName オプション名
         * @param description 説明文
         * @param action 実行する処理
         */
        fun register(name: String, optionName: String, description: String, action: (String?) -> Unit) {
            commands.add(Action.RequireOption(this, name, description, optionName, action))
        }

        /**
         * サブコマンドを持つコマンドを追加する
         * @param name コマンド名
         * @param description 説明文
         * @param action サブコマンドを追加する処理
         */
        fun container(name: String, description: String, action: Container.() -> Unit) {
            commands.add(Container(this, name, description).apply(action))
        }

        /**
         * コマンドヘルプを表示する
         */
        private fun printHelp() {
            println("Usage: $runName <Command>")
            println("Commands:")
            commands.forEach {
                println("    ${it.helpMessage}")
            }
            println("Flags:")
            Flag.list.forEach {
                println("    ${it.fullName}, ${it.shortName} -> ${it.description}")
            }
        }

        override fun execute(args: Array<String>, index: Int) {
            val commandName = args.getOrNull(index)
            val command = commands.firstOrNull { it.name.equals(commandName, true) }
            if (command != null) {
                command.execute(args, index + 1)
            } else {
                printHelp()
                if (commandName != null) {
                    printError(ErrorCodes.NotFoundCommand, "コマンドが見つかりませんでした")
                }
            }
        }
    }

    /**
     * 処理を実行するコマンド
     */
    sealed class Action(parent: Container?, name: String, description: String) : Command(parent, name, description) {
        /**
         * 単純な処理
         */
        class Simple(parent: Container, name: String, description: String, val action: () -> Unit) : Action(parent, name, description) {
            override fun execute(args: Array<String>, index: Int) {
                action()
            }
        }

        /**
         * 引数を必要とする処理
         */
        class RequireOption(parent: Container?, name: String, description: String, optionName: String, val action: (String?) -> Unit) : Action(parent, name, description) {
            override val helpMessage = "$name <$optionName> -> $description"

            override fun execute(args: Array<String>, index: Int) {
                action(args.getOrNull(index))
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
