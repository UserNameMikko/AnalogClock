package com.mikko.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikko.clock.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        binding.second.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
        binding.third.setOnClickListener {
            startActivity(Intent(this@MainActivity, ThirdActivity::class.java))
        }

    }
}