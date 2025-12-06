package com.htc.android.teeter.models

data class Level(
    val levelNumber: Int,
    val beginX: Float,
    val beginY: Float,
    val endX: Float,
    val endY: Float,
    val walls: List<Wall>,
    val holes: List<Hole>
)

data class Wall(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float
)

data class Hole(
    val x: Float,
    val y: Float
)
