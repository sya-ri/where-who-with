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

/**
 * [HttpStatusCode.BadRequest] であるかのアサートを行う
 */
fun assertBadRequest(response: TestApplicationResponse, message: String? = null) {
    assertStatusCode(HttpStatusCode.BadRequest, response, message)
}

/**
 * [HttpStatusCode.UnsupportedMediaType] であるかのアサートを行う
 */
fun assertUnsupportedMediaType(response: TestApplicationResponse, message: String? = null) {
    assertStatusCode(HttpStatusCode.UnsupportedMediaType, response, message)
}
