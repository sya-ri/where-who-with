package dev.s7a.w3.server.route.http.log.leave

import dev.s7a.w3.server.database.entity.Area
import dev.s7a.w3.server.database.entity.Log
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.database.table.Logs
import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.util.receiveOrRespondBadRequest
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.datetime.Clock
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * POST /log/leave
 *
 * エリアから出た日時を記録する
 */
suspend fun PipelineContext<Unit, ApplicationCall>.logLeavePost() {
    val request = call.receiveOrRespondBadRequest<LogRequest>() ?: return
    val log = transaction {
        val user = User.findById(request.userId) ?: return@transaction null
        val area = Area.findById(request.areaId) ?: return@transaction null
        Log.find {
            (Logs.userId eq user.id.value) and
                (Logs.areaId eq area.id.value) and
                (Logs.leaveAt eq null)
        }.singleOrNull()?.apply {
            this.leaveAt = Clock.System.now()
        }
    } ?: return call.respond(HttpStatusCode.BadRequest)
    call.respond(LogResponse(log.leaveAt!!))
}
