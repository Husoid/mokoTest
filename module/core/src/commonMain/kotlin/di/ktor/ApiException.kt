package di.ktor

import kotlinx.serialization.Serializable

@Serializable
data class ApiException(override val message: String, val code: Int) : RuntimeException(message)

data class HtmlApiException(val code: Int, override val message: String) : RuntimeException(message)