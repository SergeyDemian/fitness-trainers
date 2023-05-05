package responses

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IResponse
import ru.otus.fitness.trainers.api.v2.models.TrainingSearchResponse
import kotlin.reflect.KClass

object SearchResponseStrategy: IResponseStrategy {
    override val discriminator: String = "search"
    override val clazz: KClass<out IResponse> = TrainingSearchResponse::class
    override val serializer: KSerializer<out IResponse> = TrainingSearchResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TrainingSearchResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
