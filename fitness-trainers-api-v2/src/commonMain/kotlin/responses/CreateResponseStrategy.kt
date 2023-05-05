package responses

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IResponse
import ru.otus.fitness.trainers.api.v2.models.TrainingCreateResponse
import kotlin.reflect.KClass

object CreateResponseStrategy: IResponseStrategy {
    override val discriminator: String = "create"
    override val clazz: KClass<out IResponse> = TrainingCreateResponse::class
    override val serializer: KSerializer<out IResponse> = TrainingCreateResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TrainingCreateResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
