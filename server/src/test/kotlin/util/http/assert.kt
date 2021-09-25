package util.http

import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationResponse
import kotlin.test.assertEquals

/**
 * [HttpStatusCode] のアサートを行う
 */
fun assertStatusCode(expected: HttpStatusCode, response: TestApplicationResponse, message: String? = null) {
    assertEquals(expected, response.status(), message)
}

/**
 * [HttpStatusCode.OK] であるかのアサートを行う
 */
fun assertOK(response: TestApplicationResponse, message: String? = null) {
    assertStatusCode(HttpStatusCode.OK, response, message)
}