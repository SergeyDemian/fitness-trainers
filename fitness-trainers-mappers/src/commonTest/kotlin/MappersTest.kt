import kotlinx.datetime.Instant
import models.*
import models.stubs.FtStubs
import ru.otus.fitness.trainers.api.v2.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MappersTest {

    @Test
    fun fromTransport() {
        val request: IRequest = TrainingCreateRequest(
            requestId = "asdf",
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

        val context = FtContext()
        context.fromTransport(request)

        assertEquals(FtStubs.SUCCESS, context.stubCase)
        assertEquals(FtWorkMode.STUB, context.workMode)
        assertEquals("asdf", context.requestId.asString())
        assertEquals("1", context.trainingRequest.clientId.asString())
        assertEquals(FtLoad.HARD, context.trainingRequest.load)
        assertEquals(Instant.parse("2023-05-17T19:00:00Z"), context.trainingRequest.dateTime)
    }


    @Test
    fun toTransport() {
        val context = FtContext(
            requestId = FtRequestId("1"),
            command = FtCommand.CREATE,
            trainingResponse = FtTraining(
                dateTime = Instant.parse("2023-05-17T19:00:00Z"),
                load = FtLoad.LOW,
                clientId = FtClientId("23")
            ),
            errors = mutableListOf(
                FtErrors(
                    code = "err",
                    group = "response",
                    field = "load",
                    message = "wrong load"
                )
            ),
            state = FtState.RUNNING
        )

        val response = context.toTransport() as TrainingCreateResponse

        assertEquals("1", response.requestId)
        assertEquals("2023-05-17T19:00:00Z", response.training?.dateTime)
        assertEquals(Load.LOW, response.training?.load)
        assertEquals("23", response.training?.clientId)
        assertEquals(1, response.errors?.size)
        assertEquals("err", response.errors?.firstOrNull()?.code)
        assertEquals("response", response.errors?.firstOrNull()?.group)
        assertEquals("load", response.errors?.firstOrNull()?.field)
        assertEquals("wrong load", response.errors?.firstOrNull()?.message)
    }
}