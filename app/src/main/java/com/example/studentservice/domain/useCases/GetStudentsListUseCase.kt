package com.example.studentservice.domain.useCases

import com.example.studentservice.data.repositories.studentRepository.StudentRepository
import com.example.studentservice.presentation.studentListScreen.StudentModel
import javax.inject.Inject

class GetStudentsListUseCase @Inject constructor(
	private val repository: StudentRepository
){
	suspend operator fun invoke(): List<StudentModel> {
		return repository.getStudents()
	}
}