package dev.s7a.w3.server.cli.command

import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.database.setupDatabase

/**
 * サーバーを起動するコマンド
 */
object StartCommand : Command("start", "サーバーを起動します") {
    override fun execute(args: Array<String>) {
        setupDatabase()
    }
}
