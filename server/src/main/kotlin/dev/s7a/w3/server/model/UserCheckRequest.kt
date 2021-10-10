package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * @param deskUuid 受付ID
 * @param userId ユーザーID
 */
@Serializable
data class UserCheckRequest(
    @SerialName("desk_uuid") @Serializable(with = UUIDSerializer::class) val deskUuid: UUID,
    @SerialName("user_id") val userId: Int,
) {
    constructor(desk: Desk, user: User) : this(desk.uuid, user.id.value)
}
