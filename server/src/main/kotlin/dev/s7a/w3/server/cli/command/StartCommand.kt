package dev.s7a.w3.server.cli.command

import dev.s7a.w3.server.database.setupDatabase

/**
 * サーバーを起動するコマンド
 */
object StartCommand : Command("start", "s", "サーバーを起動します") {
    override fun execute() {
        setupDatabase()
    }
}
