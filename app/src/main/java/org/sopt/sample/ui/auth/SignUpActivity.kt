package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel by viewModels<SignUpViewModel>()
    private var isEmailCompleted = false
    private var isPWDCompleted = false
    private var isNameCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)
        binding.btnSignUp.isEnabled = false
        textStatus()
        clickSignUpBTN()
    }

    private fun signUpCheck() {
        binding.btnSignUp.isEnabled = isEmailCompleted && isPWDCompleted && isNameCompleted
    }

    private fun clickSignUpBTN() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPwd.text.toString()
            val name = binding.edtName.text.toString()
            viewModel.signUp(email, password, name)
        }
        viewModel.signUpResult.observe(this) {
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
        }
        viewModel.errorMessage.observe(this) {
            startActivity(
                Intent(
                    this@SignUpActivity,
                    SignUpActivity::class.java
                )
            )
        }
    }

    private fun passwordRegex(password: String): Boolean {
        return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{6,12}$".toRegex())
    }

    private fun emailRegex(email: String): Boolean {
        return email.matches("^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{6,10}$".toRegex())
    }

    private fun textStatus() {
//        var emailText = ""
//        var nameText = ""
//        var pwdText = ""

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                emailText = binding.edtEmail.text.toString()
//
//                binding.btnSignUp.isEnabled =
//                    emailText.isNotEmpty() && nameText.isNotEmpty() && pwdText.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    when {
                        !emailRegex(p0.toString()) -> {
                            binding.emailTil.error = "아이디 양식이 맞지 않습니다"
                            isEmailCompleted = false
                        }
                        else -> {
                            binding.emailTil.error = null
                            isEmailCompleted = true
                        }
                    }
                    signUpCheck()
                }
            }
        })

        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    when {
                        p0.isEmpty() -> {
                            binding.nameTil.error = "이름을 입력해주세요"
                            isNameCompleted = false
                        }
                        else -> {
                            binding.nameTil.error = null
                            isNameCompleted = true
                        }
                    }
                    signUpCheck()
                }
            }
        })

        binding.edtPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0 != null) {
                    when {
                        !passwordRegex(p0.toString()) -> {
                            binding.pwdTil.error = "비밀번호 양식이 맞지 않습니다"
                            isPWDCompleted = false
                        }
                        else -> {
                            binding.pwdTil.error = null
                            isPWDCompleted = true
                        }
                    }
                    signUpCheck()
                }
            }
        })
    }
}
