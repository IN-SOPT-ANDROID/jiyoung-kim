package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.databinding.ActivitySignUpBinding

// [회원가입 페이지]


// 아이디 6-10자
// 비밀번호 8-12자
//mobi 별도 조건없음
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate((layoutInflater))
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            if(binding.edtId.text.length in 6..10 && binding.edtPwd.text.length in 8..12) {
                // ???스낵바가 튀어나왔다가 화면 이동됨
                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra("id", binding.edtId.text.toString()) //id 정보를 담음
                intent.putExtra("pwd", binding.edtPwd.text.toString())  //pwd 정보를 담음
                intent.putExtra("mbti", binding.edtMbti.text.toString())  //mbti 정보를 담음
                Snackbar.make(binding.root, "회원가입이 완료되었습니다",Snackbar.LENGTH_SHORT).show()
                startActivity(intent)



            }

        }

    }
}