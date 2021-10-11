package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.table.Areas

/**
 * エリアを追加する
 * @param _name エリア名
 */
fun ExecutionPlatform.areaCreate(_name: String?) {
    checkAreaName(_name) { name ->
        val area = useDatabaseOnce {
            val notExist = Area.find { Areas.name eq name }.limit(1).empty()
            if (notExist) {
                Area.new {
                    this.name = name
                }
            } else {
                null
            }
        }
        if (area != null) {
            printSuccess("エリアを追加しました (id: ${area.id}, uuid: ${area.uuid}, name: ${area.name})")
        } else {
            printError(ErrorCode.OptionExist, "既に存在するエリア名です")
        }
    }
}
