package com.example.studentservice.data.retrofit

import com.example.studentservice.domain.dto.StudentDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentApi {
	@GET("/students")
	suspend fun getStudentsList(): List<StudentDto>

	@POST("/students")
	suspend fun addStudent(@Body student: StudentDto): StudentDto

	@PUT("/students/{id}")
	suspend fun updateStudent(@Path("id") id: Int, @Body student: StudentDto)

	@DELETE("/students/{id}")
	suspend fun deleteStudent(@Path("id") id: Int)
}
