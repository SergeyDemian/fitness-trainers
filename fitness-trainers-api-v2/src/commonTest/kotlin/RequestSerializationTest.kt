import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.otus.fitness.trainers.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class RequestSerializationTest {

    private val request = TrainingCreateRequest(
        requestId = "asdf",
        requestType = "create",
        debug = TrainingDebug(
            mode = TrainingRequestDebugMode.STUB,
            stub = TrainingRequestDebugStubs.SUCCESS
        ),
        training = TrainingCreateObject(
            clientId = "1",
            load = Load.HARD,
            dateTime = "2023-05-17T19:00:00Z"
        )
    )

    @Test
    fun serialize() {
        val json = apiV2Mapper.encodeToString(request)

        assertContains(json, Regex("\"load\":\\s*\"hard\""))
        assertContains(json, Regex("\"mode\":\\s*\"stub\""))
        assertContains(json, Regex("\"stub\":\\s*\"success\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(request)
        val obj = apiV2Mapper.decodeFromString(json) as TrainingCreateRequest

        assertEquals(request, obj)
    }
}