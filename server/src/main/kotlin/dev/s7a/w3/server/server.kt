package dev.s7a.w3.server

import dev.s7a.w3.server.route.route
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.toLogString
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

/**
 * サーバーを起動する
 */
fun startServer() {
    embeddedServer(CIO, port = 8080, module = Application::application).start(true)
}

/**
 * サーバーの設定を行う
 */
fun Application.application() {
    install(AutoHeadResponse)
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
        header(HttpHeaders.ContentType)
    }
    install(CallLogging) {
        level = Level.INFO
        logger = LoggerFactory.getLogger("Log")
        format { call ->
            val requestLog = call.request.toLogString()
            when (val status = call.response.status() ?: "Unhandled") {
                HttpStatusCode.Found -> "$status: $requestLog -> ${call.response.headers[HttpHeaders.Location]}"
                else -> "$status: $requestLog"
            }
        }
    }
    routing(Routing::route)
}
