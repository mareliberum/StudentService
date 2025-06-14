package com.example.studentservice.presentation.studentListScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.studentservice.Routes
import com.example.studentservice.presentation.components.StudentCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
	navController: NavController,
	viewModel: StudentListScreenViewModel = hiltViewModel(),
) {
	val studentsState by viewModel.studentListState.collectAsState()
	val isTeacher = viewModel.userIsTeacher
	val isRefreshing by viewModel.isRefreshing.collectAsState()
	var searchQuery by remember { mutableStateOf("") }
	val filteredStudents = when (studentsState) {
		is StudentListState.Content -> {
			val students = (studentsState as StudentListState.Content).studentsList
			if (searchQuery.isBlank()) {
				students
			} else {
				students.filter { student ->
					student.name.contains(searchQuery, ignoreCase = true)
				}
			}
		}

		else -> emptyList()
	}


	Scaffold(topBar = {
		Column {
			TopAppBar(title = {
				Text(
					"Students", style = MaterialTheme.typography.headlineSmall
				)
			})
			DockedSearchBar(
				shape = SearchBarDefaults.fullScreenShape,
				modifier = Modifier
					.fillMaxWidth()
					.height(56.dp),
				query = searchQuery,
				onQueryChange = { searchQuery = it },
				onSearch = { },
				active = true,
				onActiveChange = { },
				placeholder = { Text("Найти студента") },
				trailingIcon = { Icon(Icons.Default.Search, null) },
				colors = SearchBarDefaults.colors(
					containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
				),
			) {}
		}
	}, floatingActionButton = {
		if (isTeacher && studentsState !is StudentListState.Error) {
			FloatingActionButton(
				onClick = { navController.navigate(Routes.CreateStudentScreen.route) },
				containerColor = MaterialTheme.colorScheme.primary,
				shape = CircleShape
			) {
				Icon(Icons.Default.Add, contentDescription = "Add Student")
			}
		}
	}, floatingActionButtonPosition = FabPosition.End
	) { padding ->
		Box(modifier = Modifier.padding(padding)) {
			PullToRefreshBox(
				isRefreshing = isRefreshing,
				onRefresh = { viewModel.loadStudents() },
				modifier = Modifier.fillMaxSize(),
			) {
				val onActiveChange = { }
				val colors = SearchBarDefaults.colors(
					containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
				)

				when (val students = studentsState) {
					StudentListState.Empty -> {
						Box(
							modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
						) {
							Text(
								"Список студентов пуст", style = MaterialTheme.typography.bodyLarge
							)
						}
					}

					StudentListState.Loading -> {}
					is StudentListState.Content -> {
						val content =
							if (filteredStudents.isEmpty()) students.studentsList
							else filteredStudents
						LazyColumn(
							modifier = Modifier
								.padding(horizontal = 12.dp, vertical = 8.dp)
								.fillMaxWidth(), contentPadding = PaddingValues(bottom = 88.dp)
						) {
							items(content) { student ->
								StudentCard(student = student,
									isTeacher = isTeacher,
									onAddToken = { viewModel.addToken(student) },
									onRemoveToken = { viewModel.removeToken(student) },
									onExpel = { viewModel.expelStudent(student) })
							}
						}
					}

					is StudentListState.Error -> {
						Box(
							modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
						) {
							Text(students.message, color = MaterialTheme.colorScheme.error)
						}
					}
				}
			}
		}
	}
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
	StudentListScreen(navController = NavController(LocalContext.current))
}