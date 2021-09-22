package dev.s7a.w3.server

import dev.s7a.w3.server.cli.CLI

/**
 * メイン処理
 */
fun main(args: Array<String>) {
    val argsIgnoreFlag = CLI.processFlag(args)
    CLI.execute(argsIgnoreFlag)
}
