package dev.s7a.w3.server.cli.command.desk

import dev.s7a.w3.server.cli.ErrorCodes
import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.cli.api.RequireOption
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Desks

/**
 * 受付を削除するコマンド
 */
object DeskDeleteCommand : Command("delete", "受付を削除します"), RequireOption {
    override val optionArgumentIndex = 2

    override fun execute(args: Array<String>) {
        checkName(args) { name ->
            val desk = useDatabaseOnce {
                Desk.find { Desks.name eq name }.firstOrNull()?.apply(Desk::delete)
            }
            if (desk != null) {
                printSuccess("受付を削除しました (id: ${desk.id}, name: ${desk.name})")
            } else {
                printError(ErrorCodes.Option.NotFound, "存在しない受付名です")
            }
        }
    }
}
