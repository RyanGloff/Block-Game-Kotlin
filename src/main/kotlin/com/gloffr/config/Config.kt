package com.gloffr.config

class Config {
    val width: Int = 0
    val height: Int = 0
    val initScreen: String = "MenuScreen"
    val menuScreenSettings: MenuScreenSettings = MenuScreenSettings()
    val gameScreenSettings: GameScreenSettings = GameScreenSettings()
    val levels: List<Level> = emptyList()
}