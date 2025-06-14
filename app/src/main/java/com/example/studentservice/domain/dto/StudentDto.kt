package com.example.studentservice.domain.dto

import com.example.studentservice.presentation.studentListScreen.StudentModel

data class StudentDto(
	val id: Int,
	val name: String,
	val age: Int,
	val group: String,
	val email: String,
	val password: String,
	val tokens: Int,
)

fun StudentDto.toModel(): StudentModel {
	return StudentModel(
		id = id.toString(),
		name = name,
		tokens = tokens,
		age = age,
		group = group,
	)
}
