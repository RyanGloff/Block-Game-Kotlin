package com.gloffr

import com.gloffr.config.Level

class GameModel (private val model: Model) {

    var currentLevel: Level? = null

    var playerX: Int = 0
    var playerY: Int = 0

    var activated: Array<BooleanArray> = Array(0, {BooleanArray(0)})

    val gameHudModel = GameHudModel(model)

    fun loadLevel (level: Level) {
        activated = Array(level.tiles.size, {BooleanArray(level.tiles.get(0).size)})
        currentLevel = level
        playerX = level.startX
        playerY = level.startY
        activated[level.startY][level.startX] = true
    }

    fun levelComplete () : Boolean {
        val level = currentLevel!!
        for (row in 0 until activated.size) {
            for (col in 0 until activated[0].size) {
                if (activated[row][col] == false && level.locationExists(col, row)) {
                    return false
                }
            }
        }
        return true
    }
    fun finishLevel () {
        if (currentLevel == null) {
            model.switchToMenu()
            return
        }
        model.setLevelComplete(currentLevel!!)
        model.switchToMenu()
    }

    fun movePlayer (dx: Int, dy: Int) {
        if (currentLevel == null) return
        val level = currentLevel!!
        playerX += dx
        playerY += dy
        level.postProcessMove(model.gameModel)
    }

}