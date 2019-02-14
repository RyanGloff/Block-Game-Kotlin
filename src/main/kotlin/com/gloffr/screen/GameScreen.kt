package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color

import com.gloffr.Model

class GameScreen(model: Model) : Screen(model) {

    override fun draw (g: Graphics) {
        g.color = Color.RED
        g.fillRect(100, 100, 100, 100)
    }

}