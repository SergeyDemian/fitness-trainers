package responses

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IResponse
import ru.otus.fitness.trainers.api.v2.models.TrainingDeleteResponse
import kotlin.reflect.KClass

object DeleteResponseStrategy: IResponseStrategy {
    override val discriminator: String = "delete"
    override val clazz: KClass<out IResponse> = TrainingDeleteResponse::class
    override val serializer: KSerializer<out IResponse> = TrainingDeleteResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TrainingDeleteResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
