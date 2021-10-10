package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * @param deskUuid 受付ID
 */
@Serializable
data class UserCreateRequest(
    @SerialName("desk_uuid") @Serializable(with = UUIDSerializer::class) val deskUuid: UUID,
) {
    constructor(desk: Desk) : this(desk.uuid)
}
