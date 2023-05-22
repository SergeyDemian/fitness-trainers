package responses

import TrainingResponseSerializer
import apiV2Mapper
import ru.otus.fitness.trainers.api.v2.models.IResponse

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(TrainingResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(TrainingResponseSerializer, json) as T
