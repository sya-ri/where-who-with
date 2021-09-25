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
import org.jetbrains.exposed.sql.and
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
