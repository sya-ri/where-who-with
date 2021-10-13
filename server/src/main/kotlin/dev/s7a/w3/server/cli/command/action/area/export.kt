package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area

/**
 * エリアの一覧を出力する
 */
fun ExecutionPlatform.areaExport(_name: String?) {
    val fileName = _name ?: "areaList.csv"
    val list = listOf(listOf("id", "name", "uuid")) + useDatabaseOnce {
        Area.all().toList()
    }.map {
        listOf(it.id.value, it.name, it.uuid)
    }
    exportToCsv(fileName, list)
}
