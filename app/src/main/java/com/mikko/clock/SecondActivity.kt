package com.mikko.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikko.clock.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding : ActivitySecondBinding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.first.setOnClickListener {
            startActivity(Intent(this@SecondActivity, MainActivity::class.java))
        }
        binding.third.setOnClickListener {
            startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
        }
    }
}