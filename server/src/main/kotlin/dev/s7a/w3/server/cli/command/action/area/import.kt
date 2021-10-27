package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.table.Areas
import java.util.UUID

/**
 * エリアの一覧を一括追加する
 */
fun ExecutionPlatform.areaImport(_name: String?) {
    val fileName = _name ?: "areaList.csv"
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
                    Area.find { Areas.name eq name }.limit(1).empty().not() -> {
                        errorList.add("${index + 1}: name=$name は既に存在しています")
                    }
                    id != null && Area.findById(id) != null -> {
                        errorList.add("${index + 1}: id=$id は既に存在しています")
                    }
                    uuid != null && Area.find { Areas.uuid eq uuid }.limit(1).empty().not() -> {
                        errorList.add("${index + 1}: uuid=$uuid は既に存在しています")
                    }
                    else -> {
                        Area.new(id) {
                            this.name = name
                            if (uuid != null) {
                                this.uuid = uuid
                            }
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
    printSuccess("エリアを追加しました [$fileName -> ${successList.size}]")
}
