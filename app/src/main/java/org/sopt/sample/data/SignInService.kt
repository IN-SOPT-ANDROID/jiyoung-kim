package org.sopt.sample.data

import org.sopt.sample.data.request.RequestSignIn
import org.sopt.sample.data.response.ResponseSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("api/user/signin")
    fun login(
        @Body request: RequestSignIn
    ): Call<ResponseSignIn>
}