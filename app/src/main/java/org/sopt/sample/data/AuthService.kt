package org.sopt.sample.data

import org.sopt.sample.data.request.RequestSignIn
import org.sopt.sample.data.request.RequestSignUp
import org.sopt.sample.data.response.ResponseSignIn
import org.sopt.sample.data.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/user/signup")
    fun signUp(
        @Body request: RequestSignUp
    ): Call<ResponseSignUp>

    @POST("api/user/signin")
    fun login(
        @Body request: RequestSignIn
    ): Call<ResponseSignIn>
}
