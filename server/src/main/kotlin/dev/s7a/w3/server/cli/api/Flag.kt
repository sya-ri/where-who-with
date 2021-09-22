package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.flag.DebugFlag

/**
 * フラグオプション
 * @param fullName 名前
 * @param shortName 短縮名
 * @param description 説明
 */
abstract class Flag(
    val fullName: String,
    val shortName: String,
    val description: String,
) {
    /**
     * 処理を実行する
     * @param isSet フラグが指定されているか
     */
    abstract fun execute(isSet: Boolean)

    /**
     * 名前が一致しているか判定する。ただし、大文字小文字を区別する
     * @param name 名前
     * @return [fullName] か [shortName] に一致していれば true
     */
    fun match(name: String) = fullName.equals(name, false) || shortName.equals(name, false)

    companion object {
        /**
         * フラグ一覧
         */
        val list = listOf(
            DebugFlag
        )

        /**
         * フラグオプションの処理
         * - デバッグモードの切り替え
         * @param args 実行引数
         * @return フラグオプションを削除した実行引数
         */
        fun processFlag(args: Array<String>): Array<String> {
            val (flagNames, argsIgnoreFlag) = args.partition { it.startsWith('-') }
            list.forEach { flag ->
                flag.execute(flagNames.any(flag::match))
            }
            return argsIgnoreFlag.toTypedArray()
        }
    }
}
