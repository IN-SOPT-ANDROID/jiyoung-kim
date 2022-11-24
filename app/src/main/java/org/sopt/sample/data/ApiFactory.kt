package org.sopt.sample.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val AUTH_BASE_URL = "http://3.39.169.52:3000/"
private const val REQRES_BASE_URL = "https://reqres.in/"

object ApiFactory {
    private val loginRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AUTH_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val authService: AuthService by lazy {
        loginRetrofit.create(AuthService::class.java)
    }

    private val reqresRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl(REQRES_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    val userService: UserService by lazy {
        reqresRetrofit.create(UserService::class.java)
    }
}
