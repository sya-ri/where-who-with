package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import java.util.UUID

/**
 * ユーザーテーブル
 */
object Users : IntIdTable("users") {
    /**
     * 識別子
     */
    val uuid = uuid("uuid").clientDefault(UUID::randomUUID)

    /**
     * 受付
     */
    val deskId = reference("desk_id", Desks)
}
