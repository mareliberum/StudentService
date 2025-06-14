package com.example.studentservice.data.retrofit

import com.example.studentservice.domain.dto.AuthRequest
import com.example.studentservice.domain.dto.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
	//TODO ручка логина
	@POST("/auth/login")
	suspend fun login(@Body request: AuthRequest): AuthResponse
}