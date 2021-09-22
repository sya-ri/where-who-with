package dev.s7a.w3.server.cli.command

import dev.s7a.w3.server.cli.api.Command

/**
 * バージョンを表示するコマンド
 */
object VersionCommand : Command("version", "バージョンを表示します") {
    override fun execute(args: Array<String>) {
        val resource = ClassLoader.getSystemResourceAsStream("version.txt")
        val version = resource?.bufferedReader()?.readText() ?: "null"
        println("Version: $version")
    }
}
