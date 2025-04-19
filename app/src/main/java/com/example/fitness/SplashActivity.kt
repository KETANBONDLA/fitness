// SplashActivity.kt
package com.example.fitness

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.fitness.CardActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAY: Long = 2000 // 2 seconds delay

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // This line connects this activity to the XML layout
        setContentView(R.layout.activity_splash) // Make sure this matches your XML filename

        // Use Handler to delay the transition to MainActivity
        Handler(Looper.getMainLooper()).postDelayed({
            // Create an intent to launch MainActivity
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)

            // Close this activity so user can't go back to splash screen
            finish()
        }, SPLASH_DELAY)
    }
}