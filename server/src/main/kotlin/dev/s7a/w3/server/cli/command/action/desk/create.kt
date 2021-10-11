package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Desks

/**
 * 受付を追加する
 * @param _name 受付名
 */
fun ExecutionPlatform.deskCreate(_name: String?) {
    checkDeskName(_name) { name ->
        val desk = useDatabaseOnce {
            val notExist = Desk.find { Desks.name eq name }.limit(1).empty()
            if (notExist) {
                Desk.new {
                    this.name = name
                }
            } else {
                null
            }
        }
        if (desk != null) {
            printSuccess("受付を追加しました (id: ${desk.id}, uuid: ${desk.uuid}, name: ${desk.name})")
        } else {
            printError(ErrorCode.OptionExist, "既に存在する受付名です")
        }
    }
}
