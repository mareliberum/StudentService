package com.example.studentservice.presentation.createStudentScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentservice.domain.useCases.CreateStudentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateStudentViewModel @Inject constructor(
	private val createStudentUseCase: CreateStudentUseCase,
) : ViewModel() {
	private val _uiState = MutableStateFlow(CreateStudentUiState())
	val uiState: StateFlow<CreateStudentUiState> = _uiState
	fun onFirstNameChanged(value: String) {
		_uiState.update { it.copy(firstName = value, errorMessage = null) }
	}

	fun onLastNameChanged(value: String) {
		_uiState.update { it.copy(lastName = value, errorMessage = null) }
	}

	fun onEmailChanged(value: String) {
		_uiState.update { it.copy(email = value, errorMessage = null) }
	}

	fun onCreateClicked() {
		viewModelScope.launch {
			createStudentUseCase(
				uiState.value.firstName,
				uiState.value.lastName,
				uiState.value.email,
			)
		}
	}
}

data class CreateStudentUiState(
	val firstName: String = "",
	val lastName: String = "",
	val email: String = "",
	val errorMessage: String? = null
) {
	val isValid: Boolean
		get() = firstName.isNotBlank() && lastName.isNotBlank() && email.contains("@")
}