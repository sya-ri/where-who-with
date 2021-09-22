package dev.s7a.w3.server.cli.command.action

/**
 * バージョンを表示する
 */
fun version() {
    val resource = ClassLoader.getSystemResourceAsStream("version.txt")
    val version = resource?.bufferedReader()?.readText() ?: "null"
    println("Version: $version")
}
