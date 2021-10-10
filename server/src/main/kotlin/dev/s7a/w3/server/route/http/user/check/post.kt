package dev.s7a.w3.server.route.http.user.check

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Desks
import dev.s7a.w3.server.database.table.Users
import dev.s7a.w3.server.model.UserCheckRequest
import dev.s7a.w3.server.model.UserCheckResponse
import dev.s7a.w3.server.util.receiveOrRespondBadRequest
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * POST /user/check
 *
 * ユーザーのUUIDを確認する
 */
suspend fun PipelineContext<Unit, ApplicationCall>.userCheckPost() {
    val request = call.receiveOrRespondBadRequest<UserCheckRequest>() ?: return
    val user = transaction {
        val desk = Desk.find { Desks.uuid eq request.deskUuid }.limit(1).firstOrNull()
        desk?.let {
            User.find {
                (Users.id eq request.userId) and (Users.deskId eq desk.id)
            }.limit(1).firstOrNull()
        }
    } ?: return call.respond(HttpStatusCode.BadRequest)
    call.respond(UserCheckResponse(user))
}
