package com.htc.android.teeter

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.htc.android.teeter.utils.GamePreferences

class ScoreActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        
        val totalTime = intent.getLongExtra("totalTime", 0)
        val totalAttempts = intent.getIntExtra("totalAttempts", 0)
        
        val rank = GamePreferences.getRank(this)
        
        findViewById<TextView>(R.id.rank_caption).text = "Congratulations!\nRank: $rank"
        findViewById<TextView>(R.id.total_time_score).text = formatTime(totalTime)
        findViewById<TextView>(R.id.total_attempt_score).text = totalAttempts.toString()
        
        findViewById<Button>(R.id.btn_restart).setOnClickListener {
            GamePreferences.resetProgress(this)
            val intent = Intent(this, GameActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finishAffinity()
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
    
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Menu")
            .setMessage("What would you like to do?")
            .setPositiveButton("Quit") { _, _ ->
                finishAffinity()
            }
            .setNegativeButton("Cancel", null)
            .setNeutralButton("Reset Progress") { _, _ ->
                AlertDialog.Builder(this)
                    .setTitle("Reset Progress")
                    .setMessage("Are you sure you want to reset all progress and start from Level 1?")
                    .setPositiveButton("Yes") { _, _ ->
                        GamePreferences.resetProgress(this)
                        Toast.makeText(this, "Progress reset to Level 1", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, GameActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finishAffinity()
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
            .show()
    }
}
