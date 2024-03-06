package di.ktor

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import mobile_kmp_app_tis.module.core.KtorSetting
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import utils.Pref

internal val ktorModule = DI.Module("ktorModule") {

    bind<Pref>() with singleton { Pref(instance()) }
    bind<ResponseError>() with singleton { ResponseError() }

    bind<HttpClient>() with singleton {
        HttpClient(HttpEngineFactory().createEngine()) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }

            install(DefaultRequest)

            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 15000
                requestTimeoutMillis = 30000
            }

            expectSuccess = true

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val clientException = exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest
                    val exceptionResponse = clientException.response
                    val exceptionResponseText = exceptionResponse.bodyAsText()
                    if (exceptionResponse.contentType().toString().contains("application/json")) {
                        try {
                            throw Json.decodeFromString<ApiException>(exceptionResponseText)
                        } catch (e: SerializationException) {
                            throw ApiException(
                                //TODO Заменить на строковый ресурс
                                "Сервер вернул ошибку в неправильном формате. Обратитесь в тех. поддержку.",
                                599
                            )
                        }
                    } else if (exceptionResponse.contentType().toString().contains("text/html")) {
                        throw HtmlApiException(
                            exceptionResponse.status.value,
                            exceptionResponseText
                        )
                    } else {
                        throw ApiException(
                            exceptionResponseText,
                            exceptionResponse.status.value
                        )
                    }
                }
            }

            defaultRequest {
                url(KtorSetting.URL)
                header("Content-Type", "application/json; charset=UTF-8")
            }
        }
    }
}