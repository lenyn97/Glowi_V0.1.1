package com.example.glowi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MContenidoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcontenido)
        val imageView: ImageView = findViewById(R.id.imageViewc1)
        imageView.setOnClickListener {
            val intent = Intent(this, Catedra1_Activity::class.java)
            startActivity(intent)
        }
    }
}