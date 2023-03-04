package com.mikko.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikko.clock.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private val binding : ActivityThirdBinding by lazy { ActivityThirdBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.first.setOnClickListener {
            startActivity(Intent(this@ThirdActivity, MainActivity::class.java))
        }
        binding.second.setOnClickListener {
            startActivity(Intent(this@ThirdActivity, SecondActivity::class.java))
        }
    }
}