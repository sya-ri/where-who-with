package dev.s7a.w3.server.cli.command.action.desk

import dev.s7a.w3.server.cli.util.useDatabaseOnce
import dev.s7a.w3.server.database.entity.Desk

/**
 * 受付の一覧を表示する
 */
fun deskList() {
    val desks = useDatabaseOnce {
        Desk.all().toList()
    }
    println("受付一覧(${desks.size}):")
    desks.forEach {
        println(" - ${it.id}: ${it.name}")
    }
}