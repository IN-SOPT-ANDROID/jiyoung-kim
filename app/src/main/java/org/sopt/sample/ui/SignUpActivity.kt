package org.sopt.sample.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.ApiFactory
import org.sopt.sample.data.request.RequestSignUp
import org.sopt.sample.data.response.ResponseSignUp
import org.sopt.sample.databinding.ActivitySignUpBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)

        textStatus()
        clickSignUpBTN()
    }

    private fun clickSignUpBTN() {
        binding.btnSignUp.setOnClickListener {
            val requestSignUp = RequestSignUp(
                binding.edtEmail.text.toString(),
                binding.edtPwd.text.toString(),
                binding.edtName.text.toString()
            )
            val call: Call<ResponseSignUp> = ApiFactory.authService.signUp(requestSignUp)

            call.enqueue(object : Callback<ResponseSignUp> {
                override fun onResponse(
                    call: Call<ResponseSignUp>,
                    response: Response<ResponseSignUp>
                ) {
                    Log.d("SignUpActivity", response.body().toString())
                    if (response.isSuccessful) { // 통신 성공
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입 축하드려요 :)",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(
                            Intent(
                                this@SignUpActivity,
                                SignInActivity::class.java
                            )
                        )
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "회원가입에 실패했어요 :(",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) { // 통신 실패
                    Log.e("SignUpActivity", t.message.toString(), t)
                    startActivity(
                        Intent(
                            this@SignUpActivity,
                            SignUpActivity::class.java
                        )
                    )
                }
            })
        }
    }

    // edit text의 변화를 감지해주는 함수이다.
    private fun textStatus() {
        val isEmailFilled = binding.edtEmail.text.toString().isNotBlank()
        val isPwdFilled = binding.edtPwd.text.toString().isNotBlank()
        val isNameFilled = binding.edtName.text.toString().isNotBlank()

        val textWatcher = object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                binding.btnSignUp.isEnabled = isEmailFilled && isPwdFilled && isNameFilled
                if (isEmailFilled && isPwdFilled && isNameFilled) {
                    binding.btnSignUp.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (isEmailFilled && isPwdFilled && isNameFilled) {
                    binding.btnSignUp.isEnabled = true
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isEmailFilled && isPwdFilled && isNameFilled) {
                    binding.btnSignUp.isEnabled = true
                }
            }
        }
        binding.edtEmail.addTextChangedListener(textWatcher)
        binding.edtPwd.addTextChangedListener(textWatcher)
        binding.edtName.addTextChangedListener(textWatcher)
    }
}
