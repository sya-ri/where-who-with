package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param userId ユーザーID
 * @param areaId エリアID
 */
@Serializable
data class LogRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("area_id") val areaId: Int,
) {
    constructor(user: User, area: Area) : this(user.id.value, area.id.value)
}
