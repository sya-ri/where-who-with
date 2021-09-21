package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * ユーザーテーブル
 */
object Users : IntIdTable("users") {
    /**
     * 受付
     */
    val deskId = reference("desk_id", Desks)
}
