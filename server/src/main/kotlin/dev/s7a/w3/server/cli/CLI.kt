package dev.s7a.w3.server.cli

import dev.s7a.w3.server.cli.command.Command
import dev.s7a.w3.server.cli.command.StartCommand
import dev.s7a.w3.server.cli.command.VersionCommand

/**
 * 実行を管理するクラス
 */
object CLI {
    /**
     * コマンド一覧
     */
    private val commands = listOf(
        StartCommand,
        VersionCommand,
        HelpCommand
    )

    /**
     * コマンドを処理する
     */
    fun process(args: Array<String>) {
        val command = args.getOrNull(0)?.let { getCommand(it) } ?: HelpCommand
        command.execute()
    }

    /**
     * 名前に一致したコマンドを取得する
     */
    private fun getCommand(name: String): Command? {
        return commands.firstOrNull { it.match(name) }
    }

    /**
     * ヘルプコマンド
     */
    private object HelpCommand : Command("help", "h", "コマンドヘルプを表示します") {
        /**
         * プログラム名
         */
        private const val programName = "java -jar w3server.jar"

        override fun execute() {
            println("Usage: $programName <Option>")
            println("Options:")
            commands.forEach {
                println("    ${it.fullName}, ${it.shortName} -> ${it.description}")
            }
        }
    }
}
