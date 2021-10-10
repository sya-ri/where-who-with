import dev.s7a.w3.server.database.entity.Log
import dev.s7a.w3.server.database.entity.User
import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.model.UserCheckRequest
import dev.s7a.w3.server.model.UserCheckResponse
import dev.s7a.w3.server.model.UserCreateRequest
import dev.s7a.w3.server.model.UserCreateResponse
import org.jetbrains.exposed.sql.transactions.transaction
import util.factory.AreaFactory
import util.factory.DeskFactory
import util.factory.UserFactory
import util.find
import util.http.assertOK
import util.http.jsonBody
import util.http.jsonContent
import util.http.testPostRequest
import util.setupTestDatabase
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class HttpRequestTest {
    @BeforeTest
    fun setupDatabase() {
        setupTestDatabase()
    }

    @Test
    fun `user can be checked`() {
        val (desk, user) = transaction {
            UserFactory.create().let { it.desk to it }
        }
        testPostRequest("/user/check") {
            jsonBody(UserCheckRequest(desk, user))
        }.run {
            assertOK(response)
            val content = response.jsonContent<UserCheckResponse>()
            assertEquals(user.uuid, content.userUuid)
        }
    }

    @Test
    fun `user can be created`() {
        val desk = transaction {
            DeskFactory.create()
        }
        testPostRequest("/user/create") {
            jsonBody(UserCreateRequest(desk))
        }.run {
            assertOK(response)
            val content = response.jsonContent<UserCreateResponse>()
            val user = transaction {
                User.findById(content.userId)
            }
            assertNotNull(user)
        }
    }

    @Test
    fun `log can be created`() {
        val (user, area) = transaction {
            UserFactory.create() to AreaFactory.create()
        }
        val joinedAt = testPostRequest("/log/join") {
            jsonBody(LogRequest(user, area))
        }.run {
            assertOK(response)
            val content = response.jsonContent<LogResponse>()
            val log = transaction {
                Log.find(user, area, content.date, null).limit(1).firstOrNull()
            }
            assertNotNull(log)
            content.date
        }
        testPostRequest("/log/leave") {
            jsonBody(LogRequest(user, area))
        }.run {
            assertOK(response)
            val content = response.jsonContent<LogResponse>()
            val log = transaction {
                Log.find(user, area, joinedAt, content.date).limit(1).firstOrNull()
            }
            assertNotNull(log)
        }
    }
}
