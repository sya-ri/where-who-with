package dev.s7a.w3.server.database.entity

import dev.s7a.w3.server.database.table.Desks
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * 受付
 * @see Desks
 */
class Desk(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Desk>(Desks)

    /**
     * 識別子名
     */
    var uuid by Desks.uuid

    /**
     * 受付名
     */
    var name by Desks.name
}
