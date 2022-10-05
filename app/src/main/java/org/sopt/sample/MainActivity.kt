package org.sopt.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.sample.databinding.ActivityMainBinding

// [로그인 페이지]
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getID = intent.getStringExtra("id")
        var getPWD = intent.getStringExtra("pwd")
        binding.btnLogin.setOnClickListener(){
            val idText = binding.edtIdMain.text
            val pwdText = binding.idtPwdMain.text
            println("idText: $idText")
            println("pwdText: $pwdText")
            println("getID    $getID")
            println("getPWD    $getPWD")
            println("DDD   ${idText.toString() == getID}")
            println("GGGG  ${pwdText.toString() == getPWD}")
            if(idText.toString() == getID && pwdText.toString() == getPWD){
                intent.putExtra("name", "김지영")
                Toast.makeText(this@MainActivity, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                val intent1 = Intent(this, HomeActivity::class.java)
                startActivity(intent1)
            }
        }
        binding.btnSignup.setOnClickListener(){
            val intent2 = Intent(this, SignUpActivity::class.java)
            startActivity(intent2)
        }

    }
}