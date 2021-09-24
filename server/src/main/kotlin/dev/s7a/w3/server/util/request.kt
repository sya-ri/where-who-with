package dev.s7a.w3.server.util

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond

/**
 * [ApplicationCall.receive] を実行し、取得できなければ [HttpStatusCode.BadRequest] を返す
 * @return 取得できればその値。できなければ null
 */
suspend inline fun <reified T : Any> ApplicationCall.receiveOrRespondBadRequest(): T? = try {
    receive()
} catch (ex: ContentTransformationException) {
    respond(HttpStatusCode.BadRequest)
    null
}
