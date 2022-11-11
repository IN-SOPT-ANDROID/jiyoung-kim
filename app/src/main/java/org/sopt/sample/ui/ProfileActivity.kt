package org.sopt.sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.databinding.ActivityHomeBinding
import org.sopt.sample.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getName = intent.getStringExtra("name")
        var getMBTI = intent.getStringExtra("mbti")

        if(intent.hasExtra("name") && intent.hasExtra("mbti")){
            binding.textName.text = "이름: " + getName
            binding.textMbti.text = "MBTI: " + getMBTI


        }
    }
}