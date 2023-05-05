package requests

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IRequest
import ru.otus.fitness.trainers.api.v2.models.TrainingUpdateRequest
import kotlin.reflect.KClass

object UpdateRequestStrategy: IRequestStrategy {
    override val discriminator: String = "update"
    override val clazz: KClass<out IRequest> = TrainingUpdateRequest::class
    override val serializer: KSerializer<out IRequest> = TrainingUpdateRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TrainingUpdateRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
