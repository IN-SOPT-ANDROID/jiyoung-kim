package org.sopt.sample.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import org.sopt.sample.data.ServicePool
import org.sopt.sample.data.SignInService
import org.sopt.sample.data.request.RequestSignIn
import org.sopt.sample.data.response.ResponseSignIn
import org.sopt.sample.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private val signinService = ServicePool.signInService
    private lateinit var binding: ActivitySignInBinding
    private lateinit var startForResult: ActivityResultLauncher<Intent>
    var id: String = ""
    var pwd: String = ""
    var mbti: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        println("36")

        startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                println("31")
                Snackbar.make(binding.root, "회원가입이 완료되었습니다", Snackbar.LENGTH_SHORT).show()
                val getID = result.data?.getStringExtra("id") ?: ""
                val getPWD = result.data?.getStringExtra("pwd")?:""
                val getMBTI = result.data?.getStringExtra("mbti")?:""
                id = getID
                pwd = getPWD
                mbti = getMBTI
            }
        }
        clickLoginBTN()
        clickSignUpBTN()

    }
    private fun clickLoginBTN(){
        binding.btnLogin.setOnClickListener(){
            val idText = binding.edtIdMain.text.toString()
            val pwdText = binding.idtPwdMain.text.toString()

            if(idText == id && pwdText == pwd){
                Toast.makeText(this@SignInActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

                signinService.login(
                    RequestSignIn(
                        binding.edtIdMain.toString(),
                        binding.idtPwdMain.toString(),
                    )
                ).enqueue(object : Callback<ResponseSignIn> {
                    override fun onResponse(
                        call: Call<ResponseSignIn>,
                        response: Response<ResponseSignIn>
                    ) {
                        startActivity(
                            Intent(
                                this@SignInActivity,
                                ProfileActivity::class.java
                            )
                        )
                    }

                    override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                        startActivity(
                            Intent(
                                this@SignInActivity,
                                SignInActivity::class.java
                            )
                        )
                    }
                })
            } else {
                Toast.makeText(this@SignInActivity, "로그인에 실패했어요 :(", Toast.LENGTH_SHORT).show()

            }
//            val intent1 = Intent(this, ProfileActivity::class.java)
//            intent1.putExtra("name", "김지영")
//            intent1.putExtra("mbti",mbti)
//            startActivity(intent1)

        }
    }
    
    private fun clickSignUpBTN(){
        binding.btnSignup.setOnClickListener(){
            
            val intent = Intent(this, SignUpActivity::class.java)
            startForResult.launch(intent)
        }
    }
}