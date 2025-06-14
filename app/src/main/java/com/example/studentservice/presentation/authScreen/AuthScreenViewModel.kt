package com.example.studentservice.presentation.authScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentservice.domain.UserSession
import com.example.studentservice.domain.useCases.TryLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
	private val tryLoginUseCase: TryLoginUseCase,
//	private val tokenStorage: TokenStorage // например, DataStore обёртка
) : ViewModel() {
	private val _state = MutableStateFlow(AuthUiState())
	val state: StateFlow<AuthUiState> = _state
	fun onEmailChange(value: String) {
		_state.value = _state.value.copy(email = value)
	}

	fun onPasswordChange(value: String) {
		_state.value = _state.value.copy(password = value)
	}

	fun onLoginClicked() {
		viewModelScope.launch {
			try {
//				val response = authRepository.login(state.value.email, state.value.password)
//				tokenStorage.saveToken(response.token)
//				tokenStorage.saveRole(response.role)
//				tokenStorage.saveName(response.fullName)
				val response = tryLoginUseCase(state.value.email,state.value.password)
				val token = response.token
				val role = response.role

				if (role  == "TEACHER"){
					UserSession.TOKEN = token
					UserSession.currentUserIsTeacher = true
					_state.value = _state.value.copy(isSuccess = true)
				}
				else if (role == "STUDENT"){
					UserSession.TOKEN = token
					UserSession.currentUserIsTeacher = false
					_state.value = _state.value.copy(isSuccess = true)
				}
				Log.d("Auth", response.role)
			} catch (e: Exception) {
				Log.e("Auth","Ошибка: ${e.localizedMessage}")
			}
		}
	}
}

data class AuthUiState(
	val email: String = "",
	val password: String = "",
	val roleIsTeacher: Boolean = false,
	val isLoading: Boolean = false,
	val isSuccess: Boolean = false,
	val errorMessage: String? = null
)



