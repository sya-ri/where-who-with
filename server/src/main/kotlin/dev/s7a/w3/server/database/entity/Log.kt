package dev.s7a.w3.server.database.entity

import dev.s7a.w3.server.database.table.Logs
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * 入退室記録
 * @see Logs
 */
class Log(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Log>(Logs)

    /**
     * ユーザー
     */
    var user by User referencedOn Logs.userId

    /**
     * ユーザーID
     */
    var userId by Logs.userId

    /**
     * エリア
     */
    var area by Area referencedOn Logs.areaId

    /**
     * エリアID
     */
    var areaId by Logs.areaId

    /**
     * エリアに入った日時
     */
    var joinedAt by Logs.joinedAt

    /**
     * エリアから出た日時
     */
    var leaveAt by Logs.leaveAt
}
