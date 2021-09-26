package dev.s7a.w3.server.database

import dev.s7a.w3.server.database.table.tables
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * データベースの初期化を行う
 */
fun setupDatabase() {
    if (TransactionManager.isInitialized()) return
    Database.connect("jdbc:sqlite:database.sqlite", "org.sqlite.JDBC")
    transaction {
        create(*tables.toTypedArray())
    }
}
