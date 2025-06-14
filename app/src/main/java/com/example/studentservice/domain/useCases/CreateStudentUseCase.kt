package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.studentRepository.StudentRepository
import javax.inject.Inject

class CreateStudentUseCase @Inject constructor(
	private val studentRepository: StudentRepository
) {
	suspend operator fun invoke(firstname: String, lastname: String, email: String) {
		studentRepository.createStudent(firstname,lastname,email)
	}
}