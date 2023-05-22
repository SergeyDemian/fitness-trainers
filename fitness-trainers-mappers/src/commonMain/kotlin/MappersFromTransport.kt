import exceptions.UnknownRequestError
import kotlinx.datetime.Instant
import models.*
import models.stubs.FtStubs
import ru.otus.fitness.trainers.api.v2.models.*

fun FtContext.fromTransport(request: IRequest) = when (request) {
    is TrainingCreateRequest -> fromTransport(request)
    is TrainingReadRequest -> fromTransport(request)
    is TrainingUpdateRequest -> fromTransport(request)
    is TrainingDeleteRequest -> fromTransport(request)
    is TrainingSearchRequest -> fromTransport(request)
    else -> throw UnknownRequestError(request::class)
}

fun IRequest?.requestId() = this?.requestId?.let { FtRequestId(it) } ?: FtRequestId.NONE
fun String?.toInstant() = this?.let {  Instant.parse(it) } ?: Instant.NONE
fun String?.toClientId() = this?.let { FtClientId(it) } ?: FtClientId.NONE
fun String?.toTrainingId() = this?.let { FtTrainingId(it) } ?: FtTrainingId.NONE
fun String?.toTrainingWithId() = FtTraining(id = this.toTrainingId())
fun Load?.fromTransport() = when (this) {
    Load.LOW -> FtLoad.LOW
    Load.HARD -> FtLoad.HARD
    Load.MEDIUM -> FtLoad.MEDIUM
    else -> FtLoad.NONE
}

fun TrainingCreateObject.toInternal() = FtTraining(
    dateTime = this.dateTime.toInstant(),
    clientId = this.clientId.toClientId() ,
    load = this.load.fromTransport(),
)

fun TrainingUpdateObject.toInternal() = FtTraining(
    id = this.id.toTrainingId(),
    dateTime = this.dateTime.toInstant(),
    clientId = this.clientId.toClientId() ,
    load = this.load.fromTransport(),
)

fun TrainingSearchFilter?.toInternal() = FtTrainingFilter(
    stringSearch = this?.searchString ?: ""
)

fun FtContext.fromTransport(request: TrainingCreateRequest) {
    command = FtCommand.CREATE
    requestId = request.requestId()
    trainingRequest = request.training?.toInternal() ?: FtTraining()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun FtContext.fromTransport(request: TrainingReadRequest) {
    command = FtCommand.READ
    requestId = request.requestId()
    trainingRequest = request.training?.id.toTrainingWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun FtContext.fromTransport(request: TrainingUpdateRequest) {
    command = FtCommand.UPDATE
    requestId = request.requestId()
    trainingRequest = request.training?.toInternal() ?: FtTraining()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun FtContext.fromTransport(request: TrainingDeleteRequest) {
    command = FtCommand.DELETE
    requestId = request.requestId()
    trainingRequest = request.training?.id.toTrainingWithId()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun FtContext.fromTransport(request: TrainingSearchRequest) {
    command = FtCommand.SEARCH
    requestId = request.requestId()
    trainingFilterRequest = request.trainingFilter?.toInternal() ?: FtTrainingFilter()
    workMode = request.debug.transportToWorkMode()
    stubCase = request.debug.transportToStubCase()
}

fun TrainingDebug?.transportToWorkMode() = when (this?.mode) {
    TrainingRequestDebugMode.PROD -> FtWorkMode.PROD
    TrainingRequestDebugMode.TEST -> FtWorkMode.TEST
    TrainingRequestDebugMode.STUB -> FtWorkMode.STUB
    else -> FtWorkMode.PROD
}

fun TrainingDebug?.transportToStubCase() = when (this?.stub) {
    TrainingRequestDebugStubs.BAD_DESCRIPTION -> FtStubs.BAD_DESCRIPTION
    TrainingRequestDebugStubs.BAD_ID -> FtStubs.BAD_ID
    TrainingRequestDebugStubs.BAD_SEARCH_STRING -> FtStubs.BAD_SEARCH_STRING
    TrainingRequestDebugStubs.BAD_TITLE -> FtStubs.BAD_TITLE
    TrainingRequestDebugStubs.BAD_VISIBILITY -> FtStubs.BAD_VISIBILITY
    TrainingRequestDebugStubs.CANNOT_DELETE -> FtStubs.CANNOT_DELETE
    TrainingRequestDebugStubs.NOT_FOUND -> FtStubs.NOT_FOUND
    TrainingRequestDebugStubs.SUCCESS -> FtStubs.SUCCESS
    else -> FtStubs.NONE
}
