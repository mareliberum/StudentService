package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.authRepository.AuthRepository
import javax.inject.Inject

class TryLoginUseCase @Inject constructor(
	private val authRepository: AuthRepository,
) {
	suspend operator fun invoke(userName: String, password: String) {
		authRepository.login(userName, password)
	}
}