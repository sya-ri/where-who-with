import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.model.UserRequest
import dev.s7a.w3.server.model.UserResponse
import kotlinx.datetime.Clock
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalSerializationApi::class)
class ModelTest {
    @Test
    fun `decode LogRequest from json`() {
        val userId = 0
        val areaId = 0
        val json = "{\"user_id\":$userId,\"area_id\":$areaId}"
        assertEquals(LogRequest(userId, areaId), Json.decodeFromString(json))
    }

    @Test
    fun `encode LogResponse to json`() {
        val now = Clock.System.now()
        assertEquals("{\"date\":\"$now\"}", Json.encodeToString(LogResponse(now)))
    }

    @Test
    fun `decode UserRequest from json`() {
        val deskId = 0
        val json = "{\"desk_id\":$deskId}"
        assertEquals(UserRequest(deskId), Json.decodeFromString(json))
    }

    @Test
    fun `encode UserResponse to json`() {
        val userId = 0
        assertEquals("{\"user_id\":$userId}", Json.encodeToString(UserResponse(userId)))
    }
}
