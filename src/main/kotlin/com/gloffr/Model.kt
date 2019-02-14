package com.gloffr

import com.gloffr.config.Config
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

}