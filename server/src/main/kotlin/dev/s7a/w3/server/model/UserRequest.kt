package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.Desk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param deskId 受付ID
 */
@Serializable
data class UserRequest(
    @SerialName("desk_id") val deskId: Int,
) {
    constructor(desk: Desk) : this(desk.id.value)
}
