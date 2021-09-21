package dev.s7a.w3.server.cli.command

import dev.s7a.w3.server.cli.api.Command
import dev.s7a.w3.server.cli.api.SubCommandManager
import dev.s7a.w3.server.cli.command.desk.DeskCreateCommand
import dev.s7a.w3.server.cli.command.desk.DeskDeleteCommand
import dev.s7a.w3.server.cli.command.desk.DeskListCommand

/**
 * 受付に関するコマンド
 */
object DeskCommand : Command("desk", "d", "受付に関するコマンドです"), SubCommandManager {
    override val commands = listOf(
        DeskCreateCommand,
        DeskDeleteCommand,
        DeskListCommand,
    )

    override val optionArgumentIndex = 1

    override fun execute(args: Array<String>) {
        super.execute(args)
    }
}
