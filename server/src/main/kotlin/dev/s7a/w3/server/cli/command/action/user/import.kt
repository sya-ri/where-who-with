package dev.s7a.w3.server.cli.command.action.user

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Desks
import dev.s7a.w3.server.database.table.Users
import java.util.UUID

/**
 * 一括追加で使用する受付名
 */
private const val DeskName = "CLI"

/**
 * ユーザーの一覧を一括追加する
 */
fun ExecutionPlatform.userImport(_name: String?) {
    val fileName = _name ?: "userList.csv"
    val list = importFromCsv(fileName)
    val successList = mutableListOf<Int>()
    val errorList = mutableListOf<String>()
    useDatabaseOnce {
        list.forEachIndexed { index, row ->
            val desk = Desk.find { Desks.name eq DeskName }.limit(1).firstOrNull() ?: Desk.new {
                this.name = DeskName
            }
            val id = row["id"]?.toIntOrNull()
            if (id != null) {
                val uuid = row["uuid"]?.let {
                    try {
                        UUID.fromString(it)
                    } catch (ex: IllegalArgumentException) {
                        errorList.add("${index + 1}: $it をUUIDに変換できませんでした")
                        return@forEachIndexed
                    }
                }
                when {
                    User.findById(id) != null -> {
                        errorList.add("${index + 1}: id=$id は既に存在しています")
                    }
                    uuid != null && User.find { Users.uuid eq uuid }.limit(1).empty().not() -> {
                        errorList.add("${index + 1}: uuid=$uuid は既に存在しています")
                    }
                    else -> {
                        User.new(id) {
                            this.desk = desk
                        }
                        successList.add(index)
                    }
                }
            } else {
                errorList.add("${index + 1}: $row の id が null です")
            }
        }
    }
    if (errorList.isNotEmpty()) {
        printError(
            ErrorCode.ImportError,
            buildString {
                appendLine("インポートで問題が発生しました")
                errorList.forEach {
                    appendLine(it)
                }
            }
        )
    }
    printSuccess("ユーザーを追加しました [$fileName -> ${successList.size}]")
}
