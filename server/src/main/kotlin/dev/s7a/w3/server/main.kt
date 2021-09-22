package dev.s7a.w3.server

import dev.s7a.w3.server.cli.api.Flag
import dev.s7a.w3.server.cli.command.executeCommand

/**
 * メイン処理
 */
fun main(args: Array<String>) {
    val argsIgnoreFlag = Flag.processFlag(args)
    executeCommand(argsIgnoreFlag)
}
