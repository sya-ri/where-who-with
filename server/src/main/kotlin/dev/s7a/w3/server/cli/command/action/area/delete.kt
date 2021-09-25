package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.table.Areas

/**
 * エリアを削除する
 * @param _name エリア名
 */
fun ExecutionPlatform.areaDelete(_name: String?) {
    checkAreaName(_name) { name ->
        val desk = useDatabaseOnce {
            Area.find { Areas.name eq name }.limit(1).firstOrNull()?.apply(Area::delete)
        }
        if (desk != null) {
            printSuccess("エリアを削除しました (id: ${desk.id}, name: ${desk.name})")
        } else {
            printError(ErrorCode.OptionNotFound, "存在しないエリア名です")
        }
    }
}
