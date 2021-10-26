package dev.s7a.w3.server.cli.command.action.user

import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.User

/**
 * ユーザーの一覧を出力する
 */
fun ExecutionPlatform.userExport(_name: String?) {
    val fileName = _name ?: "userList.csv"
    val list = listOf(listOf("id", "desk_id", "uuid")) + useDatabaseOnce {
        User.all().toList().map {
            listOf(it.id.value, it.desk.id.value, it.uuid)
        }
    }
    exportToCsv(fileName, list)
}
