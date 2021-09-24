package util

import dev.s7a.w3.server.database.table.tables
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * テスト用のデータベースを初期化を行う
 */
fun setupTestDatabase() {
    Database.connect("jdbc:sqlite:test-database.sqlite", "org.sqlite.JDBC")
    transaction {
        drop(*tables.toTypedArray())
        create(*tables.toTypedArray())
    }
}
