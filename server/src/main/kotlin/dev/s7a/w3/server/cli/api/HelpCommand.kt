package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.ErrorCodes

/**
 * ヘルプコマンド
 */
class HelpCommand(
    private val parent: SubCommandManager
) : Command("help", "h", "コマンドヘルプを表示します") {
    companion object {
        /**
         * プログラム名
         */
        private const val programName = "java -jar w3server.jar"
    }

    override fun execute(args: Array<String>) {
        val optionName = buildString {
            if (parent is Command) {
                append("${parent.fullName} ")
            }
            append("<Option>")
        }
        println("Usage: $programName $optionName")
        println("Options:")
        parent.commands.forEach(::printHelp)
        printHelp(this)
        println("Flags:")
        Flag.list.forEach(::printHelp)
        if (args.getOrNull(parent.optionArgumentIndex)?.let { match(it) } == false) {
            printError(ErrorCodes.NotFoundCommand, "コマンドが見つかりませんでした")
        }
    }

    /**
     * コマンドヘルプを表示する
     */
    private fun printHelp(command: Command) {
        println("    ${command.fullName}, ${command.shortName} -> ${command.description}")
    }

    /**
     * フラグヘルプを表示する
     */
    private fun printHelp(flag: Flag) {
        println("    ${flag.fullName}, ${flag.shortName} -> ${flag.description}")
    }
}
