package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.`java-time`.datetime

/**
 * 入退出記録テーブル
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
    val joinedAt = datetime("joined_at")

    /**
     * エリアから出た日時
     */
    val leaveAt = datetime("leave_at").nullable()
}
