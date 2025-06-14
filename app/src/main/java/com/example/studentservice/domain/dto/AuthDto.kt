package com.example.studentservice.domain.dto

data class AuthRequest(
	val username: String,
	val password: String
)

data class AuthResponse(
	val token: String,
	val role: String,
	val userId: Int,
	val fullName: String
)
