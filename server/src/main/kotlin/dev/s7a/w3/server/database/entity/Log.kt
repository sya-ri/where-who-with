package dev.s7a.w3.server.database.entity

import dev.s7a.w3.server.database.table.Logs
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

/**
 * 入退出記録
 * @see Logs
 */
class Log(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Log>(Logs)

    /**
     * ユーザー
     */
    val user by User referrersOn Logs.userId

    /**
     * エリア
     */
    val area by Area referrersOn Logs.areaId

    /**
     * エリアに入った日時
     */
    val joinedAt by Logs.joinedAt

    /**
     * エリアから出た日時
     */
    val leaveAt by Logs.leaveAt
}
