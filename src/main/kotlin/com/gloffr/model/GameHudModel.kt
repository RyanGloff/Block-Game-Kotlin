package com.gloffr.model

import javax.imageio.ImageIO
import java.io.File

import com.gloffr.graphics.SpriteSheet

class GameHudModel (private val model: Model) {

    // Images
    val backBtnImg = SpriteSheet("src/main/resources/MenuScreenButtons.png", 2, 1).getSprite(0, 0)
    val resetBtnImg = ImageIO.read(File("src/main/resources/resetBtn.png"))

    // Render Locations
    val backBtn = ObjectRenderInfo(25, 25, 50)
    val resetBtn = ObjectRenderInfo(model.config.width - 75, 25, 50)

    fun returnToMenu () {
        model.switchToMenu()
    }
    
    fun resetLevel () {
        model.gameModel.resetCurrentLevel()
    }

    fun getLevelHighScore () : Int {
        if (model.gameModel.currentLevel == null) {
            return -1
        }
        return model.gameModel.currentLevel?.highScore!!
    }

    fun getScore () : Int {
        return model.gameModel.numMoves
    }

}