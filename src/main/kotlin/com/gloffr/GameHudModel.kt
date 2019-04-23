package com.gloffr

import com.gloffr.graphics.SpriteSheet

class GameHudModel (private val model: Model) {

    // Images
    val backBtnImg = SpriteSheet("src/main/resources/MenuScreenButtons.png", 2, 1).getSprite(0, 0)

    // Render Locations
    val backBtn = ObjectRenderInfo(25, 25, 50)

    fun returnToMenu () {
        model.switchToMenu()
    }

}