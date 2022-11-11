package org.sopt.sample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.sample.databinding.ActivitySignUpBinding

// 아이디 6-10자
// 비밀번호 8-12자
// mbti 별도 조건없음
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)

        clickSignUpBTN()

    }
    private fun clickSignUpBTN(){
        binding.btnSignUp.setOnClickListener {
            if(binding.edtId.text == null || binding.edtPwd.text == null || binding.edtMbti.text == null){
                Toast.makeText(this@SignUpActivity, "정보를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else if (binding.edtId.text.length in 6..10 && binding.edtPwd.text.length in 8..12){
                val intent = Intent(this, SignInActivity::class.java)
                intent.putExtra("id", binding.edtId.text.toString()) //id 정보를 담음
                intent.putExtra("pwd", binding.edtPwd.text.toString())  //pwd 정보를 담음
                intent.putExtra("mbti", binding.edtMbti.text.toString())  //mbti 정보를 담음
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this@SignUpActivity, "회원가입을 할 수 없습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}