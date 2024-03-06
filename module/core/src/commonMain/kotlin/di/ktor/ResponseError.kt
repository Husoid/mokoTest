package di.ktor

import io.ktor.utils.io.errors.IOException

class ResponseError {
    //TODO сделать всплывающее окно с ошибкой
    fun handleError(t: Throwable) {
        println(
            when (t) {
                is ApiException -> {
                    when (t.code) {
                        403 -> {
                            "Код ошибки ${t.code}, реализовать переход на экран входа.\nОшибка - ${t.message}"
                        }
                        else -> {
                            "Код ошибки ${t.code}, показать ошибку.\nОшибка - ${t.message}"
                        }
                    }
                }
                is HtmlApiException -> {
                    val parts = t.message.split("body")
                    val errorMessage: String
                    if (parts.size == 1) {
                        errorMessage = t.message
                    } else {
                        errorMessage = "<body" + parts[parts.size - 2] + "body>"
                    }
                    // errorMessage в формате Html,
                    // TODO вывести для показа в читаемом варианте
                    errorMessage
                }
                is IOException -> "Нет интернет подключения!"
                is RuntimeException -> "Превышено время ошидания ответа от сервера"
                else -> "Что-то пошло не так"
            }
        )
    }
}