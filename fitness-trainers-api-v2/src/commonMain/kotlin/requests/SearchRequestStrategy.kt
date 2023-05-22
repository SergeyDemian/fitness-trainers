package requests

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IRequest
import ru.otus.fitness.trainers.api.v2.models.TrainingSearchRequest
import kotlin.reflect.KClass

object SearchRequestStrategy: IRequestStrategy {
    override val discriminator: String = "search"
    override val clazz: KClass<out IRequest> = TrainingSearchRequest::class
    override val serializer: KSerializer<out IRequest> = TrainingSearchRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TrainingSearchRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
