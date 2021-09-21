package dev.s7a.w3.server.cli.command.desk

import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk

/**
 * 受付の一覧を表示するコマンド
 */
object DeskListCommand : Command("list", "l", "受付の一覧を表示します") {
    override fun execute(args: Array<String>) {
        val desks = useDatabaseOnce {
            Desk.all().toList()
        }
        println("受付一覧(${desks.size}):")
        desks.forEach {
            println(" - ${it.id}: ${it.name}")
        }
    }
}
