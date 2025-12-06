package com.htc.android.teeter

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.os.PowerManager
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.htc.android.teeter.game.GameView
import com.htc.android.teeter.models.GameState
import com.htc.android.teeter.utils.LevelParser

class GameActivity : AppCompatActivity() {
    
    private lateinit var gameView: GameView
    private val gameState = GameState()
    private lateinit var wakeLock: PowerManager.WakeLock
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Keep screen on
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        
        // Acquire wake lock
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
            "Teeter::GameWakeLock"
        )
        
        // Check for accelerometer
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) == null) {
            Toast.makeText(this, R.string.str_no_sensor, Toast.LENGTH_LONG).show()
            finish()
            return
        }
        
        setContentView(R.layout.activity_game)
        
        gameView = findViewById(R.id.gameView)
        
        // Setup game callbacks
        gameView.onLevelComplete = {
            onLevelComplete()
        }
        
        gameView.onFallInHole = {
            gameState.retry()
        }
        
        // Start first level
        loadLevel(1)
    }
    
    private fun loadLevel(levelNumber: Int) {
        val level = LevelParser.loadLevel(this, levelNumber)
        if (level != null) {
            gameState.startLevel(levelNumber)
            gameView.setLevel(level)
        } else {
            Toast.makeText(this, "Level $levelNumber not found", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun onLevelComplete() {
        gameState.completeLevel()
        
        if (gameState.currentLevel >= LevelParser.getTotalLevels()) {
            // Game complete
            showGameCompleteDialog()
        } else {
            // Next level
            loadLevel(gameState.currentLevel + 1)
        }
    }
    
    private fun showGameCompleteDialog() {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("totalTime", gameState.totalTime)
        intent.putExtra("totalAttempts", gameState.totalAttempts)
        startActivity(intent)
        finish()
    }
    
    override fun onPause() {
        super.onPause()
        gameView.stopSensors()
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }
    
    override fun onResume() {
        super.onResume()
        gameView.startSensors()
        if (!wakeLock.isHeld) {
            wakeLock.acquire()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        if (wakeLock.isHeld) {
            wakeLock.release()
        }
    }
    
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage(R.string.str_msg_quit)
            .setPositiveButton(R.string.str_btn_yes) { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton(R.string.str_btn_no, null)
            .show()
    }
}
