package dev.s7a.w3.server.route

import dev.s7a.w3.server.route.http.log.join.logJoinPost
import dev.s7a.w3.server.route.http.log.leave.logLeavePost
import dev.s7a.w3.server.route.http.user.check.userCheckPost
import dev.s7a.w3.server.route.http.user.create.userCreatePost
import io.ktor.http.content.defaultResource
import io.ktor.http.content.resource
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

/**
 * ルートの設定を行う
 */
fun Routing.route() {
    swaggerRoute()
    route("/user") {
        route("/check") {
            post { userCheckPost() }
        }
        route("/create") {
            post { userCreatePost() }
        }
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

/**
 * SwaggerUI のルート設定を行う
 */
fun Routing.swaggerRoute() {
    setOf(
        "favicon-16x16.png",
        "favicon-32x32.png",
        "index.html",
        "oauth2-redirect.html",
        "swagger-spec.js",
        "swagger-ui.css",
        "swagger-ui.js",
        "swagger-ui-bundle.js",
        "swagger-ui-es-bundle.js",
        "swagger-ui-es-bundle-core.js",
        "swagger-ui-standalone-preset.js"
    ).forEach(this::resource)
    defaultResource("index.html")
}
