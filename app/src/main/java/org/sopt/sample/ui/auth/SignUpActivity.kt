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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)
        binding.btnSignUp.isEnabled = false
        textStatus()
        clickSignUpBTN()
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

    private fun textStatus() {
        var emailText = ""
        var nameText = ""
        var pwdText = ""

        binding.edtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailText = binding.edtEmail.text.toString()

                binding.btnSignUp.isEnabled =
                    emailText.isNotEmpty() && nameText.isNotEmpty() && pwdText.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                nameText = binding.edtName.text.toString()

                binding.btnSignUp.isEnabled =
                    emailText.isNotEmpty() && nameText.isNotEmpty() && pwdText.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
        binding.edtPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pwdText = binding.edtPwd.text.toString()

                binding.btnSignUp.isEnabled =
                    emailText.isNotEmpty() && nameText.isNotEmpty() && pwdText.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }
}
