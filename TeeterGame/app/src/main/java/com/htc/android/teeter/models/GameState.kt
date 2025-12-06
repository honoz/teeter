package com.htc.android.teeter.models

data class GameState(
    var currentLevel: Int = 1,
    var totalTime: Long = 0,
    var totalAttempts: Int = 0,
    var levelAttempts: Int = 0,
    var levelStartTime: Long = 0
) {
    fun startLevel(level: Int) {
        currentLevel = level
        levelAttempts = 0
        levelStartTime = System.currentTimeMillis()
    }
    
    fun getLevelTime(): Long {
        return System.currentTimeMillis() - levelStartTime
    }
    
    fun completeLevel() {
        totalTime += getLevelTime()
        totalAttempts += levelAttempts
    }
    
    fun retry() {
        levelAttempts++
        levelStartTime = System.currentTimeMillis()
    }
}
