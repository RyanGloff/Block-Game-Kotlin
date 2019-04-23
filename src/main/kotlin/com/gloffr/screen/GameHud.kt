package com.gloffr.screen

import java.awt.Graphics

import com.gloffr.graphics.SpriteSheet
import com.gloffr.model.GameHudModel

class GameHud (private val model: GameHudModel) {

    
    fun render (g: Graphics) {
        g.drawImage(model.backBtnImg, model.backBtn.left, model.backBtn.top, model.backBtn.size, model.backBtn.size, null)
    }

}