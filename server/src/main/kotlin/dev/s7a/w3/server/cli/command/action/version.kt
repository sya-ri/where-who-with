package dev.s7a.w3.server.cli.command.action

import dev.s7a.w3.server.cli.api.ExecutionPlatform

/**
 * バージョンを表示する
 */
fun ExecutionPlatform.version() {
    val resource = ClassLoader.getSystemResourceAsStream("version.txt")
    val version = resource?.bufferedReader()?.readText() ?: "null"
    println("Version: $version")
}
