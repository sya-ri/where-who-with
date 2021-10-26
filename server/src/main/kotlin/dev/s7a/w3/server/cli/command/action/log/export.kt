package dev.s7a.w3.server.cli.command.action.log

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Log

/**
 * ログを出力する
 */
fun ExecutionPlatform.logExport(_name: String?) {
    val fileName = _name ?: "log.csv"
    val list = listOf(listOf("user_id", "area_id", "joinedAt", "leaveAt")) + useDatabaseOnce {
        Log.all().toList()
    }.map {
        listOf(it.userId, it.areaId, it.joinedAt, it.leaveAt)
    }
    exportToCsv(fileName, list)
}
