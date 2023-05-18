package exceptions

import models.FtCommand

class UnknownCommandException(cmd: FtCommand): RuntimeException("Unknown command $cmd ")