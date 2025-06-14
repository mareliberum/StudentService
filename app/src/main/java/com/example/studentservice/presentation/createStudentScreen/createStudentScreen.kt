package com.example.studentservice.presentation.createStudentScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun CreateStudentScreen(
	navController: NavController,
	viewModel: CreateStudentViewModel = hiltViewModel(),
) {
	val uiState by viewModel.uiState.collectAsState()

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
			.padding(24.dp),
		verticalArrangement = Arrangement.Center
	) {
		Text(
			"Добавить студента",
			style = MaterialTheme.typography.headlineMedium,
			color = MaterialTheme.colorScheme.onBackground
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = uiState.firstName,
			onValueChange = { viewModel.onFirstNameChanged(it) },
			label = { Text("Имя") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			value = uiState.lastName,
			onValueChange = { viewModel.onLastNameChanged(it) },
			label = { Text("Фамилия") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(12.dp))

		OutlinedTextField(
			value = uiState.email,
			onValueChange = { viewModel.onEmailChanged(it) },
			label = { Text("Email") },
			modifier = Modifier.fillMaxWidth()
		)

		Spacer(modifier = Modifier.height(24.dp))

		Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
			TextButton(onClick = { navController.popBackStack() }) {
				Text("Назад")
			}

			Button(
				onClick = {
					viewModel.onCreateClicked()
					navController.popBackStack()
				},
				enabled = uiState.isValid
			) {
				Text("Добавить")
			}
		}

		if (uiState.errorMessage != null) {
			Spacer(modifier = Modifier.height(16.dp))
			Text(uiState.errorMessage!!, color = MaterialTheme.colorScheme.error)
		}
	}
}

