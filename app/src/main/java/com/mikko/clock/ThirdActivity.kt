package com.mikko.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        first.setOnClickListener {
            startActivity(Intent(this@ThirdActivity, MainActivity::class.java))
        }
        second.setOnClickListener {
            startActivity(Intent(this@ThirdActivity, SecondActivity::class.java))
        }
    }
}