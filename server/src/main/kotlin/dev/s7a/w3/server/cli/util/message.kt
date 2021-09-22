package dev.s7a.w3.server.cli.util

import kotlin.system.exitProcess

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
