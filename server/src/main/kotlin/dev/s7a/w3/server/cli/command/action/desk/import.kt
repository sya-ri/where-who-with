package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Desks
import java.util.UUID

/**
 * 受付の一覧を一括追加する
 */
fun ExecutionPlatform.deskImport(_name: String?) {
    val fileName = _name ?: "deskList.csv"
    val list = importFromCsv(fileName)
    val successList = mutableListOf<Int>()
    val errorList = mutableListOf<String>()
    useDatabaseOnce {
        list.forEachIndexed { index, row ->
            val name = row["name"]
            if (name != null) {
                val id = row["id"]?.toIntOrNull()
                val uuid = row["uuid"]?.let {
                    try {
                        UUID.fromString(it)
                    } catch (ex: IllegalArgumentException) {
                        errorList.add("${index + 1}: $it をUUIDに変換できませんでした")
                        return@forEachIndexed
                    }
                }
                when {
                    Desk.find { Desks.name eq name }.limit(1).empty().not() -> {
                        errorList.add("${index + 1}: name=$name は既に存在しています")
                    }
                    id != null && Desk.findById(id) != null -> {
                        errorList.add("${index + 1}: id=$id は既に存在しています")
                    }
                    uuid != null && Desk.find { Desks.uuid eq uuid }.limit(1).empty().not() -> {
                        errorList.add("${index + 1}: uuid=$uuid は既に存在しています")
                    }
                    else -> {
                        Desk.new {
                            this.name = name
                        }
                        successList.add(index)
                    }
                }
            } else {
                errorList.add("${index + 1}: $row の name が null です")
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
    printSuccess("受付を追加しました [$fileName -> ${successList.size}]")
}
