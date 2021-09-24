package util.http

import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.setBody
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * リクエストボディーとして application/json を指定する
 */
@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> TestApplicationRequest.jsonBody(json: T) {
    jsonBody(Json.encodeToString(json))
}

/**
 * リクエストボディーとして application/json を指定する
 */
fun TestApplicationRequest.jsonBody(json: String) {
    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
    setBody(json)
}
