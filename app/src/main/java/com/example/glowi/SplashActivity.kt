package com.example.glowi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DELAY = 2000L // Tiempo en milisegundos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DELAY)


    }
}