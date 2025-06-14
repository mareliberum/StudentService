package com.example.studentservice.presentation.studentListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentservice.domain.UserSession
import com.example.studentservice.domain.useCases.ExpellStudentUseCase
import com.example.studentservice.domain.useCases.GetStudentsListUseCase
import com.example.studentservice.domain.useCases.UpdateTokensUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListScreenViewModel @Inject constructor(
	private val updateTokensUseCase: UpdateTokensUseCase,
	private val getStudentsListUseCase: GetStudentsListUseCase,
	private val expelStudentUseCase: ExpellStudentUseCase,
) : ViewModel() {
	var userIsTeacher = UserSession.currentUserIsTeacher
		private set

	private val _studentListState = MutableStateFlow<StudentListState>(StudentListState.Loading)
	val studentListState: StateFlow<StudentListState> = _studentListState

	private val _isRefreshing = MutableStateFlow(false)
	val isRefreshing : StateFlow<Boolean> = _isRefreshing.asStateFlow()

	init {
		loadStudents()
		userIsTeacher = UserSession.currentUserIsTeacher
	}

	fun loadStudents() {
		viewModelScope.launch {
			_isRefreshing.value = true
			try {
				val students = getStudentsListUseCase()
				if (students.isEmpty()) {
					_studentListState.value = StudentListState.Empty
				} else {
					_studentListState.value = StudentListState.Content(students)
				}
			} catch (e: Exception) {
				_studentListState.value = StudentListState.Error("Failed to load students")
			}
			_isRefreshing.value = false
		}
	}

	fun addToken(studentModel: StudentModel) {
		viewModelScope.launch {
			val updatedModel = studentModel.copy(tokens = studentModel.tokens + 1)
			updateTokensUseCase(updatedModel)
			loadStudents()
		}
	}

	fun removeToken(studentModel: StudentModel) {
		viewModelScope.launch {
			val updatedModel = studentModel.copy(tokens = studentModel.tokens - 1)
			updateTokensUseCase(updatedModel)
			loadStudents()
		}
	}

	fun expelStudent(student: StudentModel){
		viewModelScope.launch {
			expelStudentUseCase(student.id)
			loadStudents()
		}

	}
}

sealed interface StudentListState {
	data class Content(
		val studentsList: List<StudentModel>,
	) : StudentListState

	data class Error(
		val message: String
	) : StudentListState

	data object Loading : StudentListState
	data object Empty : StudentListState
}

