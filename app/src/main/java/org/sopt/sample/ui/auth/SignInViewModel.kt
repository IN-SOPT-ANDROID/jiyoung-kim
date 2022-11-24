package org.sopt.sample.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.sopt.sample.data.ApiFactory
import org.sopt.sample.data.request.RequestSignIn
import org.sopt.sample.data.response.ResponseSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    val isSignInSuccess = MutableLiveData(false)
    val loginResult: MutableLiveData<ResponseSignIn> = MutableLiveData()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage
    private val signInService = ApiFactory.authService

    fun login(email: String, password: String) {
        signInService.login(
            RequestSignIn(
                email,
                password
            )
        ).enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) {
                    isSignInSuccess.value = true
                    loginResult.value = response.body()
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                isSignInSuccess.value = false
                _errorMessage.value = "네트워크 상태가 좋지 않습니다"
                Log.e("SignUpActivity", t.message.toString(), t)
            }
        })
    }
}
