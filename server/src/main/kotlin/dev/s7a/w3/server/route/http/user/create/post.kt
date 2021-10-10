package dev.s7a.w3.server.route.http.user.create

import dev.s7a.w3.server.database.entity.Desk
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Desks
import dev.s7a.w3.server.model.UserCreateRequest
import dev.s7a.w3.server.model.UserCreateResponse
import dev.s7a.w3.server.util.receiveOrRespondBadRequest
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * POST /user/create
 *
 * 新しいユーザーを作成する
 */
suspend fun PipelineContext<Unit, ApplicationCall>.userCreatePost() {
    val request = call.receiveOrRespondBadRequest<UserCreateRequest>() ?: return
    val user = transaction {
        val desk = Desk.find { Desks.uuid eq request.deskUuid }.limit(1).firstOrNull()
        desk?.let {
            User.new {
                this.desk = desk
            }
        }
    } ?: return call.respond(HttpStatusCode.BadRequest)
    call.respond(UserCreateResponse(user))
}
