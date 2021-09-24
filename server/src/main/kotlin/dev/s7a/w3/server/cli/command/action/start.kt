package dev.s7a.w3.server.cli.command.action

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.database.setupDatabase
import dev.s7a.w3.server.startServer

/**
 * サーバーを起動する
 */
fun ExecutionPlatform.start() {
    setupDatabase()
    startServer()
}
