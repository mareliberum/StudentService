package com.example.studentservice.domain.dto

data class AuthRequest(
	val email: String,
	val password: String
)

data class AuthResponse(
	val token: String,
	val userId: Int,
	val role: String,
)
