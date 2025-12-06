package com.htc.android.teeter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        
        val totalTime = intent.getLongExtra("totalTime", 0)
        val totalAttempts = intent.getIntExtra("totalAttempts", 0)
        
        findViewById<TextView>(R.id.total_time_score).text = formatTime(totalTime)
        findViewById<TextView>(R.id.total_attempt_score).text = totalAttempts.toString()
        
        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            finish()
        }
        
        findViewById<Button>(R.id.btn_quit).setOnClickListener {
            finishAffinity()
        }
    }
    
    private fun formatTime(millis: Long): String {
        val seconds = (millis / 1000) % 60
        val minutes = (millis / 60000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
