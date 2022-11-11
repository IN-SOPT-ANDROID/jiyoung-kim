package org.sopt.sample.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, HomeFragment.newInstance())
            .commit()
    }
}