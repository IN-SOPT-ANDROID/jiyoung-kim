package org.sopt.sample.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.ui.home.HomeActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val viewModel by viewModels<SignInViewModel>()
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
            viewModel.login(email, password)
        }
        viewModel.loginResult.observe(this) { // response successful
            Toast.makeText(this@SignInActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT)
                .show()
            startActivity(
                Intent(
                    this@SignInActivity,
                    HomeActivity::class.java
                )
            )
        }
        viewModel.errorMessage.observe(this) {
            startActivity(
                Intent(
                    this@SignInActivity,
                    SignUpActivity::class.java
                )
            )
        }
    }

    private fun clickSignUpBTN() {
        binding.btnSignup.setOnClickListener {
            startActivity(
                Intent(
                    this@SignInActivity,
                    SignUpActivity::class.java
                )
            )
        }
    }
}
