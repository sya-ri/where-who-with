package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.table.Areas

/**
 * エリアの一覧を一括追加する
 */
fun ExecutionPlatform.areaImport(_name: String?) {
    val fileName = _name ?: "areaList.csv"
    val list = importFromCsv(fileName)
    println(list)
    val successList = mutableListOf<Int>()
    val errorList = mutableListOf<String>()
    useDatabaseOnce {
        list.forEachIndexed { index, row ->
            val name = row["name"]
            if (name != null) {
                val notExist = Area.find { Areas.name eq name }.limit(1).empty()
                if (notExist) {
                    Area.new {
                        this.name = name
                    }
                    successList.add(index)
                } else {
                    errorList.add("${index + 1}: $name は既に存在しています")
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
