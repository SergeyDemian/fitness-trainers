package responses

import kotlinx.serialization.KSerializer
import ru.otus.fitness.trainers.api.v2.models.IResponse
import ru.otus.fitness.trainers.api.v2.models.TrainingInitResponse
import kotlin.reflect.KClass

object InitResponseStrategy: IResponseStrategy {
    override val discriminator: String = "init"
    override val clazz: KClass<out IResponse> = TrainingInitResponse::class
    override val serializer: KSerializer<out IResponse> = TrainingInitResponse.serializer()
    override fun <T : IResponse> fillDiscriminator(req: T): T {
        require(req is TrainingInitResponse)
        @Suppress("UNCHECKED_CAST")
        return req.copy(responseType = discriminator) as T
    }
}
