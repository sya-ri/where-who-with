package dev.s7a.w3.server.cli.command

/**
 * バージョンを表示するコマンド
 */
object VersionCommand : Command("version", "v", "バージョンを表示します") {
    override fun execute() {
        val resource = ClassLoader.getSystemResourceAsStream("version.txt")
        val version = resource?.bufferedReader()?.readText() ?: "null"
        println("Version: $version")
    }
}
