import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import ru.otus.fitness.trainers.api.v2.models.*

@OptIn(ExperimentalSerializationApi::class)
val apiV2Mapper = Json {
    classDiscriminator = "_"
    encodeDefaults = true
    ignoreUnknownKeys = true

    serializersModule = SerializersModule {
        polymorphicDefaultSerializer(IRequest::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is TrainingCreateRequest ->  RequestSerializer(TrainingCreateRequest.serializer()) as SerializationStrategy<IRequest>
                is TrainingReadRequest   ->  RequestSerializer(TrainingReadRequest.serializer()) as SerializationStrategy<IRequest>
                is TrainingUpdateRequest ->  RequestSerializer(TrainingUpdateRequest.serializer()) as SerializationStrategy<IRequest>
                is TrainingDeleteRequest ->  RequestSerializer(TrainingDeleteRequest.serializer()) as SerializationStrategy<IRequest>
                is TrainingSearchRequest ->  RequestSerializer(TrainingSearchRequest.serializer()) as SerializationStrategy<IRequest>
                else -> null
            }
        }
        polymorphicDefault(IRequest::class) {
            TrainingRequestSerializer
        }
        polymorphicDefaultSerializer(IResponse::class) {
            @Suppress("UNCHECKED_CAST")
            when(it) {
                is TrainingCreateResponse ->  ResponseSerializer(TrainingCreateResponse.serializer()) as SerializationStrategy<IResponse>
                is TrainingReadResponse   ->  ResponseSerializer(TrainingReadResponse  .serializer()) as SerializationStrategy<IResponse>
                is TrainingUpdateResponse ->  ResponseSerializer(TrainingUpdateResponse.serializer()) as SerializationStrategy<IResponse>
                is TrainingDeleteResponse ->  ResponseSerializer(TrainingDeleteResponse.serializer()) as SerializationStrategy<IResponse>
                is TrainingSearchResponse ->  ResponseSerializer(TrainingSearchResponse.serializer()) as SerializationStrategy<IResponse>
                is TrainingInitResponse   ->  ResponseSerializer(TrainingInitResponse  .serializer()) as SerializationStrategy<IResponse>
                else -> null
            }
        }
        polymorphicDefault(IResponse::class) {
            TrainingResponseSerializer
        }

        contextual(TrainingRequestSerializer)
        contextual(TrainingResponseSerializer)
    }
}

fun Json.encodeResponse(response: IResponse): String = encodeToString(TrainingResponseSerializer, response)

fun apiV2ResponseSerialize(Response: IResponse): String = apiV2Mapper.encodeToString(TrainingResponseSerializer, Response)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2ResponseDeserialize(json: String): T = apiV2Mapper.decodeFromString(TrainingResponseSerializer, json) as T

fun apiV2RequestSerialize(request: IRequest): String = apiV2Mapper.encodeToString(TrainingRequestSerializer, request)

@Suppress("UNCHECKED_CAST")
fun <T : Any> apiV2RequestDeserialize(json: String): T = apiV2Mapper.decodeFromString(TrainingRequestSerializer, json) as T
