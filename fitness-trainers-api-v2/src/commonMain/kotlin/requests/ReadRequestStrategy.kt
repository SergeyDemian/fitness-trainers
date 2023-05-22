package requests

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IRequest
import ru.otus.fitness.trainers.api.v2.models.TrainingReadRequest
import kotlin.reflect.KClass

object ReadRequestStrategy: IRequestStrategy {
    override val discriminator: String = "read"
    override val clazz: KClass<out IRequest> = TrainingReadRequest::class
    override val serializer: KSerializer<out IRequest> = TrainingReadRequest.serializer()
    override fun <T : IRequest> fillDiscriminator(req: T): T {
        require(req is TrainingReadRequest)
        @Suppress("UNCHECKED_CAST")
        return req.copy(requestType = discriminator) as T
    }
}
