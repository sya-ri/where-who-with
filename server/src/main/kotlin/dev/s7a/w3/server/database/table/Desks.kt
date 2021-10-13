package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import java.util.UUID

/**
 * 受付テーブル
 */
object Desks : IntIdTable("desks") {
    /**
     * 名前の文字数
     */
    const val nameLength = 255

    /**
     * 識別子
     */
    val uuid = uuid("uuid").clientDefault(UUID::randomUUID).uniqueIndex()

    /**
     * 名前
     */
    val name = varchar("name", nameLength).uniqueIndex()
}
