package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * エリアテーブル
 */
object Areas : IntIdTable("areas") {
    /**
     * 名前の文字数
     */
    const val nameLength = 255

    /**
     * 名前
     */
    val name = varchar("name", 255)
}
