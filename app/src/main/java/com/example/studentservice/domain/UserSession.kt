package com.example.studentservice.domain

import javax.inject.Singleton

@Singleton
object UserSession {
	var currentUserIsTeacher:Boolean = false
}