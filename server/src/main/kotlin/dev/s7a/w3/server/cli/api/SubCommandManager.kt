package dev.s7a.w3.server.cli.api

/**
 * サブコマンドを管理するクラス
 */
interface SubCommandManager : RequireOption {
    /**
     * コマンド一覧
     */
    val commands: List<Command>

    /**
     * フラグオプションの処理
     * - デバッグモードの切り替え
     * @param args 実行引数
     * @return フラグオプションを削除した実行引数
     */
    fun processFlag(args: Array<String>): Array<String> {
        val (flagNames, argsIgnoreFlag) = args.partition { it.startsWith('-') }
        Flag.list.forEach { flag ->
            flag.execute(flagNames.any(flag::match))
        }
        return argsIgnoreFlag.toTypedArray()
    }

    /**
     * コマンドを処理する
     */
    fun execute(args: Array<String>) {
        val command = args.getOrNull(optionArgumentIndex)?.let { getCommand(it) } ?: HelpCommand(this)
        command.execute(args)
    }

    /**
     * 名前に一致したコマンドを取得する
     */
    private fun getCommand(name: String): Command? {
        return commands.firstOrNull { it.match(name) }
    }
}
