package requests

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IRequest
import ru.otus.fitness.trainers.api.v2.models.TrainingDeleteRequest
import kotlin.reflect.KClass

object DeleteRequestStrategy: IRequestStrategy {
    override val discriminator: String = "delete"
    override val clazz: KClass<out IRequest> = TrainingDeleteRequest::class
    override val serializer: KSerializer<out IRequest> = TrainingDeleteRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TrainingDeleteRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
