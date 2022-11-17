package org.sopt.sample.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.ApiFactory
import org.sopt.sample.data.request.RequestSignIn
import org.sopt.sample.data.response.ResponseSignIn
import org.sopt.sample.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickLoginBTN()
        clickSignUpBTN()
    }

    private fun clickLoginBTN() {
        Log.d("SignIn", "46")
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailMain.text.toString()
            val password = binding.idtPwdMain.text.toString()

            val requestSignIn = RequestSignIn(
                email,
                password
            )
            val call: Call<ResponseSignIn> = ApiFactory.authService.login(requestSignIn)

            call.enqueue(object : Callback<ResponseSignIn> {
                override fun onResponse(
                    call: Call<ResponseSignIn>,
                    response: Response<ResponseSignIn>
                ) {
                    if (response.isSuccessful) {
                        Log.d("SignIn", "64")
                        Toast.makeText(this@SignInActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT)
                            .show()
                        startActivity(
                            Intent(
                                this@SignInActivity,
                                HomeActivity::class.java
                            )
                        )
                    } else if (response.code() == 404) {
                        Log.d("SignIn", "73")
                        Toast.makeText(
                            this@SignInActivity,
                            "네트워크 연결이 원활하지 않습니다 ;(",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else if (response.code() == 401) {
                        Log.d("SignIn", "81")
                        Toast.makeText(this@SignInActivity, "인증방식이 만료되었습니다 ;(", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                    Log.d("SignIn", "88")
                    Toast.makeText(this@SignInActivity, "로그인에 실패했어요 :(", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun clickSignUpBTN() {
        binding.btnSignup.setOnClickListener() {
            startActivity(
                Intent(
                    this@SignInActivity,
                    SignUpActivity::class.java
                )
            )
        }
    }
}
