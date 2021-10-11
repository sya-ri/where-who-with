import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.model.UserCheckRequest
import dev.s7a.w3.server.model.UserCheckResponse
import dev.s7a.w3.server.model.UserCreateRequest
import dev.s7a.w3.server.model.UserCreateResponse
import kotlinx.datetime.Clock
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalSerializationApi::class)
class ModelTest {
    @Test
    fun `decode LogRequest from json`() {
        val userUuid = UUID.randomUUID()
        val areaUuid = UUID.randomUUID()
        val json = """
            {"user_uuid":"$userUuid","area_uuid":"$areaUuid"}
        """.trimIndent()
        assertEquals(LogRequest(userUuid, areaUuid), Json.decodeFromString(json))
    }

    @Test
    fun `encode LogResponse to json`() {
        val now = Clock.System.now()
        val json = """
            {"date":"$now"}
        """.trimIndent()
        assertEquals(json, Json.encodeToString(LogResponse(now)))
    }

    @Test
    fun `decode UserCheckRequest from json`() {
        val deskUuid = UUID.randomUUID()
        val userId = 0
        val json = """
            {"desk_uuid":"$deskUuid","user_id":$userId}
        """.trimIndent()
        assertEquals(UserCheckRequest(deskUuid, userId), Json.decodeFromString(json))
    }

    @Test
    fun `encode UserCheckResponse to json`() {
        val userUuid = UUID.randomUUID()
        val json = """
            {"user_uuid":"$userUuid"}
        """.trimIndent()
        assertEquals(json, Json.encodeToString(UserCheckResponse(userUuid)))
    }

    @Test
    fun `decode UserCreateRequest from json`() {
        val deskUuid = UUID.randomUUID()
        val json = """
            {"desk_uuid":"$deskUuid"}
        """.trimIndent()
        assertEquals(UserCreateRequest(deskUuid), Json.decodeFromString(json))
    }

    @Test
    fun `encode UserCreateResponse to json`() {
        val userId = 0
        val userUuid = UUID.randomUUID()
        val json = """
            {"user_id":$userId,"user_uuid":"$userUuid"}
        """.trimIndent()
        assertEquals(json, Json.encodeToString(UserCreateResponse(userId, userUuid)))
    }
}
