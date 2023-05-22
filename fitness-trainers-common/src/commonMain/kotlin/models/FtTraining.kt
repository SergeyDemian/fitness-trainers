package models

import NONE
import kotlinx.datetime.Instant

data class FtTraining(
    var id: FtTrainingId = FtTrainingId.NONE,
    var dateTime: Instant = Instant.NONE ,
    var clientId: FtClientId = FtClientId.NONE,
    var load: FtLoad = FtLoad.NONE,
    var ownerId: FtUserId = FtUserId.NONE,
    var permissionsClient: MutableList<FtPermissionsClient> = mutableListOf()
)
