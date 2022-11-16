package org.sopt.sample.data

import org.sopt.sample.data.request.RequestSignUp
import org.sopt.sample.data.response.ResponseSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("api/user/signup")
    fun signUp(
        @Body request: RequestSignUp
    ): Call<ResponseSignUp>
}