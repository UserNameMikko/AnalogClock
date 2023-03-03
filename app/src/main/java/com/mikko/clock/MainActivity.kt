package com.mikko.clock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        second.setOnClickListener {
            startActivity(Intent(this@MainActivity, SecondActivity::class.java))
        }
        third.setOnClickListener {
            startActivity(Intent(this@MainActivity, ThirdActivity::class.java))
        }

    }
}