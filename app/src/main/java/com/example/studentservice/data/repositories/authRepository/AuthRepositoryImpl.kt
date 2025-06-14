package com.example.studentservice.data.repositories.authRepository

import com.example.studentservice.data.retrofit.AuthApi
import com.example.studentservice.domain.dto.AuthRequest
import com.example.studentservice.domain.dto.AuthResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
	private val api: AuthApi
) : AuthRepository {
	override suspend fun login(username: String, password: String): AuthResponse {
		return withContext(Dispatchers.IO){
			api.login(AuthRequest(username, password))
		}
	}
}