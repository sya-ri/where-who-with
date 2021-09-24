package dev.s7a.w3.server.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

/**
 * @param date 日時
 */
@Serializable
data class LogResponse(
    val date: Instant,
)
