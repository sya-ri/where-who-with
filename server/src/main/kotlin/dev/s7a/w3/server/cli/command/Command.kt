package dev.s7a.w3.server.cli.command

/**
 * コマンド
 * @param fullName 名前
 * @param shortName 短縮名
 * @param description 説明
 */
abstract class Command(
    val fullName: String,
    val shortName: String,
    val description: String
) {
    /**
     * コマンドを実行する
     */
    abstract fun execute()

    /**
     * 名前が一致しているか判定する
     * @param name 名前
     * @return [fullName] か [shortName] に一致していれば true
     */
    fun match(name: String) = fullName.equals(name, true) || shortName.equals(name, true)
}
