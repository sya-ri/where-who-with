package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.util.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * @param userId 作成したユーザーID
 * @param userUuid 作成したユーザーID
 */
@Serializable
data class UserResponse(
    @SerialName("user_id") val userId: Int,
    @SerialName("user_uuid") @Serializable(with = UUIDSerializer::class) val userUuid: UUID,
) {
    constructor(user: User) : this(user.id.value, user.uuid)
}
