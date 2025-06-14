package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.authRepository.AuthRepository
import com.example.studentservice.domain.dto.AuthResponse
import javax.inject.Inject

class TryLoginUseCase @Inject constructor(
	private val authRepository: AuthRepository,
) {
	suspend operator fun invoke(email: String, password: String): AuthResponse {
		return authRepository.login(email, password)
	}
}