package org.sopt.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getName = intent.getStringExtra("name")
        var getMBTI = intent.getStringExtra("mbti")


        println(getName)
        println(getMBTI)

        if(intent.hasExtra("name") && intent.hasExtra("mbti")){
            binding.textView1.text = getName
            binding.textView2.text = getMBTI

        }
    }
}