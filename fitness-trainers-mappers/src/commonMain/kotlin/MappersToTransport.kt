import exceptions.UnknownCommandException
import models.*
import ru.otus.fitness.trainers.api.v2.models.*

fun FtContext.toTransport() : IResponse = when (command) {
    FtCommand.CREATE -> toTransportCreate()
    FtCommand.READ ->   toTransportRead()
    FtCommand.UPDATE -> toTransportUpdate()
    FtCommand.DELETE -> toTransportDelete()
    FtCommand.SEARCH -> toTransportSearch()
    else -> throw UnknownCommandException(command)
}

fun FtContext.toTransportCreate() = TrainingCreateResponse(
    responseType = "create",
    requestId = requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == FtState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    training = trainingResponse.toTransportTraining()
)

fun FtContext.toTransportRead() = TrainingReadResponse(
    responseType = "create",
    requestId = requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == FtState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    training = trainingResponse.toTransportTraining()
)

fun FtContext.toTransportUpdate() = TrainingUpdateResponse(
    responseType = "create",
    requestId = requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == FtState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    training = trainingResponse.toTransportTraining()
)

fun FtContext.toTransportDelete() = TrainingDeleteResponse(
    responseType = "create",
    requestId = requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == FtState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    training = trainingResponse.toTransportTraining()
)

fun FtContext.toTransportSearch() = TrainingSearchResponse(
    responseType = "create",
    requestId = requestId.asString().takeIf { it.isNotBlank() },
    result = if (state == FtState.RUNNING) ResponseResult.SUCCESS else ResponseResult.ERROR,
    errors = errors.toTransportErrors(),
    trainings = trainingsResponse.toTransportTraining()
)

fun FtTraining.toTransportTraining() = TrainingResponseObject(
    id = id.takeIf {it != FtTrainingId.NONE}?.asString(),
    dateTime = dateTime.toString(),
    load = load.toTransportTraining(),
    clientId = clientId.takeIf { it != FtClientId.NONE }?.asString(),
    permissions = permissionsClient.toTransportPermissions()
)

fun List<FtTraining>.toTransportTraining(): List<TrainingResponseObject>? = this
    .map { it.toTransportTraining() }
    .toList()
    .takeIf { it.isNotEmpty() }

fun FtLoad.toTransportTraining() = when (this) {
    FtLoad.HARD -> Load.HARD
    FtLoad.MEDIUM -> Load.MEDIUM
    FtLoad.LOW -> Load.LOW
    else -> null
}

fun MutableList<FtPermissionsClient>.toTransportPermissions() = map (::toTransportPermission).toSet().takeIf { it.isNotEmpty() }

fun toTransportPermission(permission: FtPermissionsClient) = when (permission) {
    FtPermissionsClient.DELETE -> TrainingPermissions.DELETE
    FtPermissionsClient.READ -> TrainingPermissions.READ
    FtPermissionsClient.UPDATE -> TrainingPermissions.UPDATE
    FtPermissionsClient.MAKE_VISIBLE_PUBLIC -> TrainingPermissions.MAKE_VISIBLE_PUBLIC
    FtPermissionsClient.MAKE_VISIBLE_TO_GROUP -> TrainingPermissions.MAKE_VISIBLE_GROUP
    FtPermissionsClient.MAKE_VISIBLE_TO_OWNER -> TrainingPermissions.MAKE_VISIBLE_OWN
}

fun MutableList<FtErrors>.toTransportErrors() = map(::toTransportError).toList().takeIf { it.isNotEmpty() }

fun toTransportError(error: FtErrors) = Error(
    code = error.code.takeIf { it.isNotBlank() },
    group = error.group.takeIf { it.isNotBlank() },
    field = error.field.takeIf { it.isNotBlank() },
    message = error.message.takeIf { it.isNotBlank() }
)

