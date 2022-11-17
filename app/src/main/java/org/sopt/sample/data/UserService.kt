package org.sopt.sample.data

import org.sopt.sample.data.response.ResponseUser
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("api/users?page=2")
    fun getUser(): Call<ResponseUser>
}