import dev.s7a.w3.server.model.LogRequest
import dev.s7a.w3.server.model.LogResponse
import dev.s7a.w3.server.model.UserRequest
import dev.s7a.w3.server.model.UserResponse
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
        val json = "{\"user_uuid\":\"$userUuid\",\"area_uuid\":\"$areaUuid\"}"
        assertEquals(LogRequest(userUuid, areaUuid), Json.decodeFromString(json))
    }

    @Test
    fun `encode LogResponse to json`() {
        val now = Clock.System.now()
        assertEquals("{\"date\":\"$now\"}", Json.encodeToString(LogResponse(now)))
    }

    @Test
    fun `decode UserRequest from json`() {
        val deskUuid = UUID.randomUUID()
        val json = "{\"desk_uuid\":\"$deskUuid\"}"
        assertEquals(UserRequest(deskUuid), Json.decodeFromString(json))
    }

    @Test
    fun `encode UserResponse to json`() {
        val userId = 0
        val userUuid = UUID.randomUUID()
        assertEquals("{\"user_id\":$userId,\"user_uuid\":\"$userUuid\"}", Json.encodeToString(UserResponse(userId, userUuid)))
    }
}
