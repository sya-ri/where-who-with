package dev.s7a.w3.server

import dev.s7a.w3.server.route.route
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.serialization.json
import io.ktor.server.cio.CIO
import io.ktor.server.engine.embeddedServer

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
    install(ContentNegotiation) {
        json()
    }
    install(CORS) {
        anyHost()
        header(HttpHeaders.ContentType)
    }
    routing(Routing::route)
}
