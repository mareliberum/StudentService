package com.example.studentservice.data.di

import com.example.studentservice.data.retrofit.AuthApi
import com.example.studentservice.data.retrofit.StudentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideBaseUrl(): String = "https://modern-marmot-eminently.ngrok-free.app"

	@Provides
	fun provideLoggingInterceptor(): HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply {
			setLevel(HttpLoggingInterceptor.Level.BODY)
		}
	}

	@Provides
	fun provideOkHttpClient(
		loggingInterceptor: HttpLoggingInterceptor
	): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(loggingInterceptor)
			.build()
	}

	@Provides
	fun provideRetrofit(
		baseUrl: String,
		client: OkHttpClient
	): Retrofit {
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()
	}

	@Provides
	fun provideStudentApi(retrofit: Retrofit): StudentApi {
		return retrofit.create(StudentApi::class.java)


	}
	@Provides
	fun provideAuthApi(retrofit: Retrofit): AuthApi {
		return retrofit.create(AuthApi::class.java)
	}

}