package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import java.util.UUID

/**
 * エリアテーブル
 */
object Areas : IntIdTable("areas") {
    /**
     * 名前の文字数
     */
    const val nameLength = 255

    /**
     * 識別子
     */
    val uuid = uuid("uuid").clientDefault(UUID::randomUUID)

    /**
     * 名前
     */
    val name = varchar("name", 255)
}
