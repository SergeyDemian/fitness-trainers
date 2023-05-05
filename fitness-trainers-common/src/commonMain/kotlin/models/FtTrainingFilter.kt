package models

import NONE
import kotlinx.datetime.Instant

data class FtTrainingFilter(
    var stringSearch: String = "",
    var ownerId: FtUserId = FtUserId.NONE
)
