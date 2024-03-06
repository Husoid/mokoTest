package data

import api.AuthRepository
import api.models.LoginData
import data.responce.KtorLoginResponce
import data.responce.mapToLoginData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.post
import io.ktor.util.InternalAPI
import mobile_kmp_app_tis.module.core.BuildConfig

class AuthRepositoryImpl(private val httpClient: HttpClient) : AuthRepository {

    @OptIn(InternalAPI::class)
    override suspend fun login(phone: String, pinCode: String): LoginData = httpClient.post {
        body = MultiPartFormDataContent(
            formData {
                append("tokenApp", BuildConfig.TOKEN_APP)
                append("uuid", "fa94c76b496e4318")
                append("sessId", "compose_kmp")
                append("command", "auth")
                append("phone", "79220067760")
                append("pinCode", "1982")
            }
        )
    }.body<KtorLoginResponce>().mapToLoginData()
}
