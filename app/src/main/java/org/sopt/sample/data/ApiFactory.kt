package org.sopt.sample.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object ApiFactory {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(getHttpClient())
            .build()
    }
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object ServicePool {
    val signUpService = ApiFactory.create<SignUpService>()
    val signInService = ApiFactory.create<SignInService>()
}

private fun getHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()

    // client에 로깅인터셉터, 기본 파라메터 추가
    with(client) {
        connectTimeout(60, TimeUnit.SECONDS) // 연결 타임아웃
        readTimeout(60, TimeUnit.SECONDS) // 읽기 타임아웃
        writeTimeout(60, TimeUnit.SECONDS) // 쓰기 타임아웃
        retryOnConnectionFailure(true) // 실패시 다시 시도
    }

    return client.build()
}
