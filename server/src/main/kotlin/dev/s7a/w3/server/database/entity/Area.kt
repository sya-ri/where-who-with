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
     * 識別子名
     */
    var uuid by Areas.uuid

    /**
     * エリア名
     */
    var name by Areas.name
}
