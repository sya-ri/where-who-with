package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Desks

/**
 * 受付を削除する
 * @param _name 受付名
 */
fun ExecutionPlatform.deskDelete(_name: String?) {
    checkDeskName(_name) { name ->
        val desk = useDatabaseOnce {
            Desk.find { Desks.name eq name }.limit(1).firstOrNull()?.apply(Desk::delete)
        }
        if (desk != null) {
            printSuccess("受付を削除しました (id: ${desk.id}, uuid: ${desk.uuid}, name: ${desk.name})")
        } else {
            printError(ErrorCode.OptionNotFound, "存在しない受付名です")
        }
    }
}
