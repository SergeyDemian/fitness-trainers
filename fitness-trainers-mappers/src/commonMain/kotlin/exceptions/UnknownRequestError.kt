package exceptions

import kotlin.reflect.KClass

class UnknownRequestError(clazz: KClass<*>): RuntimeException("Class $clazz can't be mapped and not supported")