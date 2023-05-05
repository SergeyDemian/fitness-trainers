package requests

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IRequest
import ru.otus.fitness.trainers.api.v2.models.TrainingCreateRequest
import kotlin.reflect.KClass

object CreateRequestStrategy: IRequestStrategy {
    override val discriminator: String = "create"
    override val clazz: KClass<out IRequest> = TrainingCreateRequest::class
    override val serializer: KSerializer<out IRequest> = TrainingCreateRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TrainingCreateRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
