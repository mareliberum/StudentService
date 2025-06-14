package com.example.studentservice.data.repositories.studentRepository

import com.example.studentservice.data.retrofit.StudentApi
import com.example.studentservice.domain.dto.StudentDto
import com.example.studentservice.domain.dto.toModel
import com.example.studentservice.presentation.studentListScreen.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(
	private val api: StudentApi,
) : StudentRepository {

	override suspend fun updateTokens(updatedModel: StudentModel) {
		updateStudent(updatedModel)
	}

	override suspend fun getStudents(): List<StudentModel> {
		return withContext(Dispatchers.IO) {

			api.getStudentsList().map { it.toModel() }
		}
	}

	override suspend fun createStudent(firstName: String, lastName: String, email: String) {
		val dto = StudentDto(
			id = 0,
			name = "$firstName $lastName",
			age = 18, // заглушка
			group = "IU3-41B",
			email = email,
			password = "default",
			tokens = 0
		)
		withContext(Dispatchers.IO) {
			api.addStudent(dto)
		}
	}
	override suspend fun updateStudent(updatedModel: StudentModel) {
		val dto = StudentDto(
			id = updatedModel.id.toInt(),
			name = updatedModel.name,
			age = 20,
			group = updatedModel.group,
			email = "test@example.com",
			password = "mock",
			tokens = updatedModel.tokens
		)
		withContext(Dispatchers.IO){
			api.updateStudent(dto.id, dto)
		}
	}

	override suspend fun expelStudent(studentId: String) {
		withContext(Dispatchers.IO) {
			api.deleteStudent(studentId.toInt())
		}
	}
}
