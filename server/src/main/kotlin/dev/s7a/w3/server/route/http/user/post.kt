package dev.s7a.w3.server.route.http.user

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.model.UserRequest
import dev.s7a.w3.server.model.UserResponse
import dev.s7a.w3.server.util.receiveOrRespondBadRequest
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * POST /user
 *
 * 新しいユーザーを作成する
 */
suspend fun PipelineContext<Unit, ApplicationCall>.userPost() {
    val request = call.receiveOrRespondBadRequest<UserRequest>() ?: return
    val user = transaction {
        val desk = Desk.findById(request.deskId)
        desk?.let {
            User.new {
                this.desk = desk
            }
        }
    } ?: return call.respond(HttpStatusCode.BadRequest)
    call.respond(UserResponse(user))
}
