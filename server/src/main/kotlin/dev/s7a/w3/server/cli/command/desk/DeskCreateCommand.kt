package dev.s7a.w3.server.cli.command.desk

import dev.s7a.w3.server.cli.ErrorCodes
import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.cli.api.RequireOption
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.table.Desks

/**
 * 受付を追加するコマンド
 */
object DeskCreateCommand : Command("create", "受付を追加します"), RequireOption {
    override val optionArgumentIndex = 2

    override fun execute(args: Array<String>) {
        checkName(args) { name ->
            val desk = useDatabaseOnce {
                val notExist = Desk.find { Desks.name eq name }.empty()
                if (notExist) {
                    Desk.new {
                        this.name = name
                    }
                } else {
                    null
                }
            }
            if (desk != null) {
                printSuccess("受付を追加しました (id: ${desk.id}, name: ${desk.name})")
            } else {
                printError(ErrorCodes.Option.Exist, "既に存在する受付名です")
            }
        }
    }
}
