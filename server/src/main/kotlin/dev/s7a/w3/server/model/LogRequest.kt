package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * @param userUuid ユーザーID
 * @param areaUuid エリアID
 */
@Serializable
data class LogRequest(
    @SerialName("user_uuid") @Serializable(with = UUIDSerializer::class) val userUuid: UUID,
    @SerialName("area_uuid") @Serializable(with = UUIDSerializer::class) val areaUuid: UUID,
) {
    constructor(user: User, area: Area) : this(user.uuid, area.uuid)
}
