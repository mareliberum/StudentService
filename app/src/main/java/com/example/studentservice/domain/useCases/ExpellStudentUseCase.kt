package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.studentRepository.StudentRepository
import javax.inject.Inject

class ExpellStudentUseCase @Inject constructor(
	private val studentRepository: StudentRepository
) {
	suspend operator fun invoke(studentId :String){
		studentRepository.expelStudent(studentId)
	}
}