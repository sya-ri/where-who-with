package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.ErrorCodes
import dev.s7a.w3.server.cli.api.ExecutionPlatform
import dev.s7a.w3.server.database.table.Desks

/**
 * 受付名の確認を行う
 * @param name 受付名
 * @param onSuccess 成功時の処理
 */
inline fun ExecutionPlatform.checkDeskName(name: String?, onSuccess: (String) -> Unit) {
    if (name != null) {
        if (name.length <= Desks.nameLength) {
            onSuccess(name)
        } else {
            printError(ErrorCodes.Option.IllegalFormat, "受付の名前は255文字以下にしてください")
        }
    } else {
        printError(ErrorCodes.Option.NotEnter, "追加する受付の名前を入力してください")
    }
}
