package dev.s7a.w3.server.route

import dev.s7a.w3.server.route.http.log.join.logJoinPost
import dev.s7a.w3.server.route.http.log.leave.logLeavePost
import dev.s7a.w3.server.route.http.user.userPost
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

/**
 * ルートの設定を行う
 */
fun Routing.route() {
    route("/user") {
        post { userPost() }
    }
    route("/log") {
        route("/join") {
            post { logJoinPost() }
        }
        route("/leave") {
            post { logLeavePost() }
        }
    }
}
