package dev.s7a.w3.server.cli.command.action

import dev.s7a.w3.server.database.setupDatabase

/**
 * サーバーを起動する
 */
fun start() {
    setupDatabase()
}
