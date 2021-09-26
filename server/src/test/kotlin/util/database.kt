package util

import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.Log
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Logs
import dev.s7a.w3.server.database.table.tables
import kotlinx.datetime.Instant
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * データベースのテーブルを再作成する
 */
private fun <T : Table> recreate(vararg tables: T, inBatch: Boolean = false) {
    drop(*tables, inBatch = inBatch)
    create(*tables, inBatch = inBatch)
}

/**
 * テスト用のデータベースを初期化を行う
 */
fun setupTestDatabase() {
    Database.connect("jdbc:sqlite:test-database.sqlite", "org.sqlite.JDBC")
    transaction {
        recreate(*tables.toTypedArray())
    }
}

/**
 * @see Log.Companion.find
 */
fun Log.Companion.find(
    user: User,
    area: Area,
    joinedAt: Instant,
    leaveAt: Instant?
) = find {
    (Logs.userId eq user.id) and
        (Logs.areaId eq area.id) and
        (Logs.joinedAt eq joinedAt) and
        (Logs.leaveAt eq leaveAt)
}
