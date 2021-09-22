package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.util.ShellColor
import kotlin.system.exitProcess

/**
 * コマンド
 * @param fullName 名前
 * @param shortName 短縮名
 * @param description 説明
 */
abstract class Command(
    val fullName: String,
    val shortName: String,
    val description: String,
) {
    /**
     * コマンドを実行する
     */
    abstract fun execute(args: Array<String>)

    /**
     * 名前が一致しているか判定する。ただし、大文字小文字を区別しない
     * @param name 名前
     * @return [fullName] か [shortName] に一致していれば true
     */
    fun match(name: String) = fullName.equals(name, true) || shortName.equals(name, true)

    /**
     * 成功メッセージを表示する
     * @param message メッセージ
     */
    fun printSuccess(message: String) {
        println("${ShellColor.Green}SUCCESS: $message${ShellColor.Reset}")
    }

    /**
     * エラーを表示する
     * @param errorCode エラーコード
     * @param message エラーの本文
     */
    fun printError(errorCode: Int, message: String) {
        println("${ShellColor.Red}ERROR: $message${ShellColor.Reset}")
        exitProcess(errorCode)
    }
}
