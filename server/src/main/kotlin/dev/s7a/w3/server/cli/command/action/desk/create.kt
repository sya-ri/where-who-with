package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.ErrorCodes
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
            val notExist = Desk.find { Desks.name eq name }.empty()
            if (notExist) {
                Desk.new {
                    this.name = name
                }
            } else {
                null
            }
        }
        if (desk != null) {
            printSuccess("受付を追加しました (id: ${desk.id}, name: ${desk.name})")
        } else {
            printError(ErrorCodes.Option.Exist, "既に存在する受付名です")
        }
    }
}
