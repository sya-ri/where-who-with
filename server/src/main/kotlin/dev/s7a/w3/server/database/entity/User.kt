package dev.s7a.w3.server.database.entity

import dev.s7a.w3.server.database.table.Users
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * ユーザー
 * @see Users
 */
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    /**
     * 受付
     */
    var desk by Desk referencedOn Users.deskId
}
