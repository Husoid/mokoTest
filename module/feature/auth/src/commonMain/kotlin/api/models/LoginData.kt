package api.models

data class LoginData(
    val needChangePin: Boolean,
    val tokenUser: String,
    val userName: String,
    val dispatcherPhone: String?,
    val driverAWPWorkMode: String?
)
