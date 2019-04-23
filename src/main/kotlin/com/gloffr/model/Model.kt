package com.gloffr.model

import com.gloffr.config.Config
import com.gloffr.config.Level
import com.gloffr.screen.Screen
import com.gloffr.screen.MenuScreen
import com.gloffr.screen.GameScreen

class Model(val config: Config) {

    val menuScreen = MenuScreen(this)
    val gameModel = GameModel(this)
    val gameScreen = GameScreen(this)

    var activeScreen: Screen
    val stateSaver: SaveStateController

    init {
        if (config.initScreen == "GameScreen") {
            activeScreen = gameScreen
        } else {
            activeScreen = menuScreen
        }
        stateSaver = SaveStateController(this)
        stateSaver.readState()
    }

    fun setLevelComplete (level: Level) {
        val index = config.levels.indexOf(level)
        level.status = LevelStatus.COMPLETE
        if (index != config.levels.size - 1) {
            config.levels[index + 1].status = LevelStatus.AVAILABLE
        }
        stateSaver.saveState()
    }

    fun startGame (level: Level) {
        activeScreen = gameScreen
        gameModel.loadLevel(level)
    }

    fun resumeGame () {
        if (gameModel.currentLevel == null) return
        activeScreen = gameScreen
    }

    fun switchToMenu () {
        activeScreen = menuScreen
    }

    fun resetLevels () {
        config.levels.forEachIndexed{ index, level ->
            if (index != 0) {
                level.status = LevelStatus.LOCKED
            } else {
                level.status = LevelStatus.AVAILABLE
            }
        }
        stateSaver.saveState()
    }

}