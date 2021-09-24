package dev.s7a.w3.server.model

import dev.s7a.w3.server.database.entity.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param userId 作成したユーザーID
 */
@Serializable
data class UserResponse(
    @SerialName("user_id") val userId: Int,
) {
    constructor(user: User) : this(user.id.value)
}
