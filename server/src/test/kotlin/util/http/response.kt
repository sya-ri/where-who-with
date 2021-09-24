package util.http

import io.ktor.server.testing.TestApplicationResponse
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * [TestApplicationResponse.content] を Json として [T] に変換します
 */
@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> TestApplicationResponse.jsonContent(): T {
    return Json.decodeFromString(content!!)
}
