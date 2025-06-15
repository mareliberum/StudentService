package com.example.studentservice.data.repositories.authRepository

import com.example.studentservice.domain.dto.AuthResponse

interface AuthRepository {
	suspend fun login(username: String, password: String): AuthResponse
}