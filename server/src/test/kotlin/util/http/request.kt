package util.http

import dev.s7a.w3.server.application
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.server.testing.TestApplicationCall
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.TestApplicationRequest
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication

private fun <R> withBaseTestApplication(test: TestApplicationEngine.() -> R): R {
    return withTestApplication(Application::application, test)
}

/**
 * POST リクエストをテストする
 */
fun testPostRequest(uri: String, setup: TestApplicationRequest.() -> Unit): TestApplicationCall {
    return withBaseTestApplication {
        handleRequest(HttpMethod.Post, uri, setup)
    }
}
