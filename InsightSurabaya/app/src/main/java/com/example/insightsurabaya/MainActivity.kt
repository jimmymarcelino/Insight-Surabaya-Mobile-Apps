package com.example.insightsurabaya

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var count =0
        var gambar = findViewById<ImageView>(R.id.imageView2)

        gambar.setOnClickListener {
            val intent = Intent(this@MainActivity,Home::class.java)
            startActivity(intent)
        }

        object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {
            }
            override fun onFinish() {
                val intent = Intent(this@MainActivity,Home::class.java)
                startActivity(intent)
            }
        }.start()

    }
}