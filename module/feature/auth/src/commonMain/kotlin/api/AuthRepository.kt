package api

import api.models.LoginData

interface AuthRepository {
    suspend fun login(phone: String, pinCode: String): LoginData
}