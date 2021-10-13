package dev.s7a.w3.server.cli.api

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.util.ShellColor
import java.io.File
import java.lang.StringBuilder
import kotlin.system.exitProcess

/**
 * 実行環境
 */
interface ExecutionPlatform {
    /**
     * CSV ファイルからデータを読み込む
     */
    fun importFromCsv(fileName: String): List<Map<String, String>> {
        return csvReader().readAllWithHeader(File(fileName))
    }

    /**
     * CSV ファイルにデータを書き込む
     */
    fun exportToCsv(fileName: String, data: List<List<Any?>>) {
        csvWriter().writeAll(data, File(fileName))
    }

    /**
     * メッセージを表示する
     */
    fun printMessage(builderAction: StringBuilder.() -> Unit) {
        printMessage(buildString(builderAction))
    }

    /**
     * メッセージを表示する
     */
    fun printMessage(message: String) {
        println(message)
    }

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
    fun printError(errorCode: ErrorCode, message: String)

    /**
     * コマンド
     */
    object Command : ExecutionPlatform {
        override fun printError(errorCode: ErrorCode, message: String) {
            println("${ShellColor.Red}ERROR: $message${ShellColor.Reset}")
            exitProcess(errorCode.exitCode)
        }
    }

    /**
     * 対話モード
     */
    object Interact : ExecutionPlatform {
        override fun printError(errorCode: ErrorCode, message: String) {
            println("${ShellColor.Red}ERROR[${errorCode.exitCode}]: $message${ShellColor.Reset}")
        }
    }
}
