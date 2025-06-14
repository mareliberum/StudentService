package com.example.studentservice.data.di

import com.example.studentservice.data.repositories.authRepository.AuthRepository
import com.example.studentservice.data.repositories.authRepository.AuthRepositoryImpl
import com.example.studentservice.data.retrofit.StudentApi
import com.example.studentservice.data.repositories.studentRepository.StudentRepository
import com.example.studentservice.data.repositories.studentRepository.StudentRepositoryImpl
import com.example.studentservice.data.retrofit.AuthApi
import com.example.studentservice.domain.useCases.UpdateTokensUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

	@Provides
	fun providesStudentRepository(studentApi: StudentApi) : StudentRepository {
		return StudentRepositoryImpl(studentApi)
	}

	@Provides
	fun providesUpdateTokenUseCase(repository: StudentRepository) : UpdateTokensUseCase {
		return UpdateTokensUseCase(repository)
	}

	@Provides
	fun providesAuthRepository(authApi: AuthApi) : AuthRepository{
		return AuthRepositoryImpl(authApi)
	}


}