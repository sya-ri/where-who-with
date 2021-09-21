package dev.s7a.w3.server.cli

import dev.s7a.w3.server.cli.api.SubCommandManager
import dev.s7a.w3.server.cli.command.DeskCommand
import dev.s7a.w3.server.cli.command.StartCommand
import dev.s7a.w3.server.cli.command.VersionCommand

/**
 * 実行を管理するクラス
 */
object CLI : SubCommandManager {
    override val commands = listOf(
        StartCommand,
        DeskCommand,
        VersionCommand,
    )

    override val optionArgumentIndex = 0
}
