package com.example.studentservice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentservice.presentation.authScreen.LoginScreen
import com.example.studentservice.presentation.createStudentScreen.CreateStudentScreen
import com.example.studentservice.presentation.studentListScreen.StudentListScreen
import com.example.studentservice.ui.theme.StudentServiceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen()
		enableEdgeToEdge()
		setContent {
			StudentServiceTheme {
				App()
			}
		}
	}
}

@Composable
fun App() {
	val navController = rememberNavController()

	NavHost(navController = navController, startDestination = Routes.AuthScreen.route) {
		composable(Routes.StudentListScreen.route) { StudentListScreen(navController) }
		composable(Routes.AuthScreen.route) { LoginScreen(navController) }
		composable(Routes.CreateStudentScreen.route) { CreateStudentScreen(navController) }

	}
}

sealed class Routes(val route: String) {
	data object StudentListScreen : Routes("StudentList")
	data object AuthScreen : Routes("Auth")
	data object CreateStudentScreen : Routes("Create")
}