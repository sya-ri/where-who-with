package dev.s7a.w3.server.database.entity

import dev.s7a.w3.server.database.table.Areas
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * エリア
 * @see Areas
 */
class Area(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Area>(Areas)

    /**
     * エリア名
     */
    val name by Areas.name
}