package dev.s7a.w3.server.cli.command

import dev.s7a.w3.server.cli.api.Command.Companion.registerCommand
import dev.s7a.w3.server.cli.command.action.desk.deskCreate
import dev.s7a.w3.server.cli.command.action.desk.deskDelete
import dev.s7a.w3.server.cli.command.action.desk.deskList
import dev.s7a.w3.server.cli.command.action.start
import dev.s7a.w3.server.cli.command.action.version

/**
 * コマンドを処理する
 * @param args 実行引数
 */
fun executeCommand(args: Array<String>) {
    val rootCommand = registerCommand("java -jar w3server.jar") {
        register("start", "サーバーを起動します", ::start)
        container("desk", "受付に関するコマンドです") {
            register("create", "受付名", "受付を追加します", ::deskCreate)
            register("delete", "受付名", "受付を削除します", ::deskDelete)
            register("list", "受付の一覧を表示します", ::deskList)
        }
        register("version", "バージョンを表示します", ::version)
    }
    rootCommand.execute(args, 0)
}
