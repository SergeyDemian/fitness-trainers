package requests

import TrainingRequestSerializer
import apiV2Mapper
import ru.otus.fitness.trainers.api.v2.models.IRequest



fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(TrainingRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(TrainingRequestSerializer, json) as T
