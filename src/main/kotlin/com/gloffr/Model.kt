package com.gloffr

import com.gloffr.config.Config
import com.gloffr.config.Level
import com.gloffr.screen.Screen
import com.gloffr.screen.MenuScreen
import com.gloffr.screen.GameScreen

class Model(val config: Config) {

    val menuScreen = MenuScreen(this)
    val gameScreen = GameScreen(this)

    var activeScreen: Screen

    init {
        if (config.initScreen == "GameScreen") {
            activeScreen = gameScreen
        } else {
            activeScreen = menuScreen
        }
    }

    fun setLevelComplete (level: Level) {
        val index = config.levels.indexOf(level)
        level.complete = true
        if (index != config.levels.size - 1) {
            config.levels[index + 1].locked = false
        }
    }

    fun startGame (level: Level) {
        activeScreen = gameScreen
        gameScreen.loadLevel(level)
    }

    fun resumeGame () {
        if (gameScreen.currentLevel == null) return
        activeScreen = gameScreen
    }

    fun switchToMenu () {
        activeScreen = menuScreen
    }

}