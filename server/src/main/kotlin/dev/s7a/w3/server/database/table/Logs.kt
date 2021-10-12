package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

/**
 * 入退室記録テーブル
 */
object Logs : IntIdTable("logs") {
    /**
     * ユーザー
     */
    val userId = reference("user_id", Users)

    /**
     * エリア
     */
    val areaId = reference("area_id", Areas)

    /**
     * エリアに入った日時
     */
    val joinedAt = timestamp("joined_at")

    /**
     * エリアから出た日時
     */
    val leaveAt = timestamp("leave_at").nullable()
}
