package dev.s7a.w3.server.cli.api

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.util.ShellColor
import kotlin.system.exitProcess

/**
 * 実行環境
 */
sealed class ExecutionPlatform {
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
    abstract fun printError(errorCode: ErrorCode, message: String)

    /**
     * コマンド
     */
    object Command : ExecutionPlatform() {
        override fun printError(errorCode: ErrorCode, message: String) {
            println("${ShellColor.Red}ERROR: $message${ShellColor.Reset}")
            exitProcess(errorCode.exitCode)
        }
    }

    /**
     * 対話モード
     */
    object Interact : ExecutionPlatform() {
        override fun printError(errorCode: ErrorCode, message: String) {
            println("${ShellColor.Red}ERROR[${errorCode.exitCode}]: $message${ShellColor.Reset}")
        }
    }
}
