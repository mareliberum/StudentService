package com.example.studentservice.data.repositories.studentRepository

import com.example.studentservice.presentation.studentListScreen.StudentModel

interface StudentRepository {
	suspend fun updateTokens(updatedModel: StudentModel)
	suspend fun getStudents(): List<StudentModel>
	suspend fun createStudent(firstName: String, lastName: String, email: String)
	suspend fun updateStudent(updatedModel: StudentModel)
	suspend fun expelStudent(studentId: String)
}