package dev.s7a.w3.server.route.http.log.join

import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.Log
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Areas
import dev.s7a.w3.server.database.table.Logs
import dev.s7a.w3.server.database.table.Users
import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.util.receiveOrRespondBadRequest
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * POST /log/join
 *
 * エリアに入った日時を記録する
 */
suspend fun PipelineContext<Unit, ApplicationCall>.logJoinPost() {
    val request = call.receiveOrRespondBadRequest<LogRequest>() ?: return
    val log = transaction {
        val user = User.find { Users.uuid eq request.userUuid }.limit(1).firstOrNull() ?: return@transaction null
        val area = Area.find { Areas.uuid eq request.areaUuid }.limit(1).firstOrNull() ?: return@transaction null
        Log.find {
            (Logs.userId eq user.id.value) and
                (Logs.areaId eq area.id.value) and
                (Logs.leaveAt eq null)
        }.limit(1).firstOrNull()?.apply {
            this.joinedAt = Clock.System.now()
        } ?: Log.new {
            this.user = user
            this.area = area
            this.joinedAt = Clock.System.now()
        }
    } ?: return call.respond(HttpStatusCode.BadRequest)
    call.respond(LogResponse(log.joinedAt))
}
