package dev.s7a.w3.server.database.table

import org.jetbrains.exposed.dao.id.IntIdTable

/**
 * 受付テーブル
 */
object Desks : IntIdTable("desks") {
    /**
     * 名前
     */
    val name = varchar("name", 255).uniqueIndex()
}
