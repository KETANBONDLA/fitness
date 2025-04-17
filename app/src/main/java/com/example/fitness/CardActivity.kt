package com.example.fitness

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)

        val bmi = findViewById<ImageView>(R.id.imageView3)
        val fat = findViewById<ImageView>(R.id.imageView2)


        bmi.setOnClickListener {
            val i = Intent(this, BMI::class.java)
            startActivity(i)
        }

        fat.setOnClickListener {
            val i2 = Intent(this, fat::class.java)
            startActivity(i2)
        }


    }
}


