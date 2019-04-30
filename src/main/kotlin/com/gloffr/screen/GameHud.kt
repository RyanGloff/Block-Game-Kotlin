package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color
import java.awt.Font

import com.gloffr.graphics.SpriteSheet
import com.gloffr.model.GameHudModel

class GameHud (private val model: GameHudModel) {

    
    fun render (g: Graphics) {
        g.drawImage(model.backBtnImg, model.backBtn.left, model.backBtn.top, model.backBtn.size, model.backBtn.size, null)
        g.drawImage(model.resetBtnImg, model.resetBtn.left, model.resetBtn.top, model.resetBtn.size, model.resetBtn.size, null)
        if (model.getLevelHighScore() != Int.MAX_VALUE) {
            g.color = Color.RED
            g.font = Font("TimesRoman", Font.PLAIN, 15)
            g.drawString("Highscore: ${model.getLevelHighScore()}", 25, 100)
        }
    }

}