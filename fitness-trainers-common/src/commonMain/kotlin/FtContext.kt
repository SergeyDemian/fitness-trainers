import kotlinx.datetime.Instant
import models.*
import models.stubs.FtStubs

data class FtContext(
    var command: FtCommand = FtCommand.NONE,
    var state: FtState = FtState.NONE,
    var errors: MutableList<FtErrors> = mutableListOf(),

    var workMode: FtWorkMode = FtWorkMode.PROD,
    var stubCase: FtStubs = FtStubs.NONE,

    var requestId: FtRequestId = FtRequestId.NONE,
    var timeStart: Instant = Instant.NONE,
    var trainingRequest: FtTraining = FtTraining(),
    var trainingFilterRequest: FtTrainingFilter = FtTrainingFilter(),
    var trainingResponse: FtTraining = FtTraining(),
    var trainingsResponse: MutableList<FtTraining> = mutableListOf()
)