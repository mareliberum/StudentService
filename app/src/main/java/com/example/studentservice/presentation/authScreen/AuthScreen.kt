package com.example.studentservice.presentation.authScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.studentservice.Routes

@Composable
fun LoginScreen(
	navController: NavController,
	viewModel: AuthViewModel = hiltViewModel(),
) {
	val uiState by viewModel.state.collectAsState()
	LaunchedEffect(uiState.isSuccess) {
		if (uiState.isSuccess) {
			navController.navigate(Routes.StudentListScreen.route){
				popUpTo(0)
			}
		}
	}


	Column(
		modifier = Modifier
			.background(MaterialTheme.colorScheme.background)
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center,
		) {
		Text(
			"Вход",
			style = MaterialTheme.typography.headlineMedium,
			color = MaterialTheme.colorScheme.onBackground
		)

		Spacer(modifier = Modifier.height(24.dp))

		// Почта
		OutlinedTextField(
			value = uiState.email,
			onValueChange = { viewModel.onEmailChange(it) },
			label = { Text("Почта") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(12.dp))
		// Пароль
		OutlinedTextField(
			value = uiState.password,
			onValueChange = { viewModel.onPasswordChange(it) },
			label = { Text("Пароль") },
			modifier = Modifier.fillMaxWidth(),
			visualTransformation = PasswordVisualTransformation()
		)

		Spacer(modifier = Modifier.height(16.dp))
		// Кнопка входа
		Button(
			onClick = { viewModel.onLoginClicked() },
			modifier = Modifier.fillMaxWidth()
		) {
			Text("Войти")
		}

	}
}

