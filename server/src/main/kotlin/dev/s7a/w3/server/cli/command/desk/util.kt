package dev.s7a.w3.server.cli.command.desk

import dev.s7a.w3.server.cli.ErrorCodes
import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.cli.api.RequireOption
import dev.s7a.w3.server.database.table.Desks

/**
 * 名前の確認を行う
 * @param args 実行引数
 * @param onSuccess 成功時の処理
 */
inline fun <T> T.checkName(args: Array<String>, onSuccess: (String) -> Unit) where T : Command, T : RequireOption {
    val name = args.getOrNull(optionArgumentIndex)
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
