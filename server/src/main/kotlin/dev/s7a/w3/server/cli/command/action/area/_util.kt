package dev.s7a.w3.server.cli.command.action.area

import dev.s7a.w3.server.cli.ErrorCode
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.database.table.Areas

/**
 * エリア名の確認を行う
 * @param name エリア名
 * @param onSuccess 成功時の処理
 */
inline fun ExecutionPlatform.checkAreaName(name: String?, onSuccess: (String) -> Unit) {
    if (name != null) {
        if (name.length <= Areas.nameLength) {
            onSuccess(name)
        } else {
            printError(ErrorCode.OptionIllegalFormat, "エリアの名前は255文字以下にしてください")
        }
    } else {
        printError(ErrorCode.OptionNotEnter, "追加するエリアの名前を入力してください")
    }
}
