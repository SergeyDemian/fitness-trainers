import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import ru.otus.fitness.trainers.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ResponseSerializationTest {
    private val response: IResponse = TrainingCreateResponse(
        responseType = "create",
        requestId = "123",
        training = TrainingResponseObject(
            load = Load.HARD,
            dateTime = "2023-05-17T19:00:00Z",
            clientId = "3"

        )
    )

    @Test
    fun serialize() {
        val json = apiV2Mapper.encodeToString(response)

        println(json)

        assertContains(json, Regex("\"load\":\\s*\"hard\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(response)
        val obj = apiV2Mapper.decodeFromString(json) as TrainingCreateResponse

        assertEquals(response, obj)
    }
}
