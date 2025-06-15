package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.studentRepository.StudentRepository
import com.example.studentservice.presentation.studentListScreen.StudentModel
import javax.inject.Inject

class UpdateTokensUseCase @Inject constructor(
	private val studentRepository: StudentRepository
) {
	suspend operator fun invoke(updatedModel: StudentModel) {
		studentRepository.updateTokens(updatedModel)
	}
}