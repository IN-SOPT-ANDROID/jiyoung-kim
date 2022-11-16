package org.sopt.sample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.sample.data.ServicePool
import org.sopt.sample.data.request.RequestSignUp
import org.sopt.sample.data.response.ResponseSignUp
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private val signupService = ServicePool.signUpService
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)
        clickSignUpBTN()
    }

    private fun clickSignUpBTN() {
        binding.btnSignUp.setOnClickListener {
            // 하나라도 공백을 입력했을 때
            Log.d("check11","32")
            if (binding.edtId.text == null || binding.edtPwd.text == null || binding.edtName.text == null) {
                Log.d("check11","34")
                Toast.makeText(this@SignUpActivity, "정보를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (binding.edtId.text.length in 6..10 && binding.edtPwd.text.length in 8..12) {
                Log.d("check11","42")
                signupService.signUp(
                    RequestSignUp(
                        binding.edtId.toString(),
                        binding.edtPwd.toString(),
                        binding.edtName.toString()
                    )
                ).enqueue(object : Callback<ResponseSignUp> {
                    override fun onResponse(
                        call: Call<ResponseSignUp>,
                        response: Response<ResponseSignUp>
                    ) {
                        Log.d("check11","542")
                        Log.d("check11",response.body().toString())
                        if (response.isSuccessful) {  // 통신 성공
                            Log.d("check","53")
                            startActivity(
                                Intent(
                                    this@SignUpActivity,
                                    SignInActivity::class.java
                                )
                            )
                            Log.d("check11","60")
                        } else {
                            Log.d("check11","62")
                            Toast.makeText(
                                this@SignUpActivity,
                                "회원가입에 실패했어요 :(",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) { // 통신 실패
                        Log.d("check11","68")
//                        startActivity(
//                            Intent(
//                                this@SignUpActivity,
//                                SignUpActivity::class.java
//                            )
//                        )
                    }

                })
            }
        }
    }
}
