package com.htc.android.teeter

import android.animation.AnimatorInflater
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    
    private val splashDelay = 2000L
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        // Start game after delay
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }, splashDelay)
    }
}
