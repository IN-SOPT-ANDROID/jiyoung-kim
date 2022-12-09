// package org.sopt.sample.ui.home
//
// import android.util.Log
// import androidx.lifecycle.LiveData
// import androidx.lifecycle.MutableLiveData
// import androidx.lifecycle.ViewModel
// import org.sopt.sample.data.ApiFactory
// import org.sopt.sample.data.request.RequestSignUp
// import org.sopt.sample.data.response.ResponseSignUp
// import retrofit2.Call
// import retrofit2.Callback
// import retrofit2.Response
//
// class HomeViewModel : ViewModel() {
//    val isLoadSuccess = MutableLiveData(false)
//    val signUpResult: MutableLiveData<ResponseSignUp> = MutableLiveData()
//    private val _errorMessage = MutableLiveData<String>()
//    val errorMessage: LiveData<String> get() = _errorMessage
//    private val signUpService = ApiFactory.authService
//
//    fun signUp(email: String, password: String, name: String) {
//        signUpService.signUp(
//            RequestSignUp(email, password, name)
//        ).enqueue(object : Callback<ResponseSignUp> {
//            override fun onResponse(
//                call: Call<ResponseSignUp>,
//                response: Response<ResponseSignUp>
//            ) {
//                Log.d("SignUpActivity", response.body().toString())
//                if (response.isSuccessful) { // 통신 성공
//                    isLoadSuccess.value = true
//                    signUpResult.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) { // 통신 실패
//                isLoadSuccess.value = false
//                _errorMessage.value = "네트워크 상태가 좋지 않습니다"
//                Log.e("SignUpActivity", t.message.toString(), t)
//            }
//        })
//    }
// }
