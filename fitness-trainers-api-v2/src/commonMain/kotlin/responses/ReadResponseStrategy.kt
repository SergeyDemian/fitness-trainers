package responses

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IResponse
import ru.otus.fitness.trainers.api.v2.models.TrainingReadResponse
import kotlin.reflect.KClass

object ReadResponseStrategy: IResponseStrategy {
    override val discriminator: String = "read"
    override val clazz: KClass<out IResponse> = TrainingReadResponse::class
    override val serializer: KSerializer<out IResponse> = TrainingReadResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TrainingReadResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
