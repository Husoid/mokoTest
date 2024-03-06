package data.responce

import api.models.LoginData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorLoginResponce(
    @SerialName("needChangePin") val needChangePin: Boolean,
    @SerialName("tokenUser") val tokenUser: String,
    @SerialName("userName") val userName: String,
    @SerialName("dispatcherPhone") val dispatcherPhone: String?,
    @SerialName("driverAWPWorkMode") val driverAWPWorkMode: String?
)

fun KtorLoginResponce.mapToLoginData(): LoginData =
    LoginData(
        needChangePin = needChangePin,
        tokenUser = tokenUser,
        userName = userName,
        dispatcherPhone = dispatcherPhone,
        driverAWPWorkMode = driverAWPWorkMode
    )
