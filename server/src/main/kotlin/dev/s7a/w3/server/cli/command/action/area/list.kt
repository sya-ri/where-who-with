package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area

/**
 * エリアの一覧を表示する
 */
fun ExecutionPlatform.areaList() {
    val areas = useDatabaseOnce {
        Area.all().toList()
    }
    printMessage {
        appendLine("エリア一覧(${areas.size}):")
        areas.forEach {
            appendLine(" - ${it.id}[${it.uuid}]: ${it.name}")
        }
    }
}
