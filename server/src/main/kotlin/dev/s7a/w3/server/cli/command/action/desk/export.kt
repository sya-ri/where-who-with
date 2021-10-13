package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk

/**
 * 受付の一覧を出力する
 */
fun ExecutionPlatform.deskExport(_name: String?) {
    val fileName = _name ?: "deskList.csv"
    val list = listOf(listOf("id", "name", "uuid")) + useDatabaseOnce {
        Desk.all().toList()
    }.map {
        listOf(it.id.value, it.name, it.uuid)
    }
    exportToCsv(fileName, list)
}
