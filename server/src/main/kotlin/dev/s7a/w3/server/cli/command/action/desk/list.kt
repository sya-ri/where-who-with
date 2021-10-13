package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk

/**
 * 受付の一覧を表示する
 */
fun ExecutionPlatform.deskList() {
    val desks = useDatabaseOnce {
        Desk.all().toList()
    }
    printMessage {
        appendLine("受付一覧(${desks.size}):")
        desks.forEach {
            appendLine(" - ${it.id}[${it.uuid}]: ${it.name}")
        }
    }
}
