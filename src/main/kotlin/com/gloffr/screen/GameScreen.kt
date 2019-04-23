package com.gloffr.screen

import com.gloffr.Model
import com.gloffr.config.Level
import com.gloffr.TileDrawer

import java.awt.Graphics
import java.awt.Color
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File


class GameScreen (model: Model) : Screen (model) {

    val tileDrawer: TileDrawer
    val playerImage: BufferedImage

    init {
        tileDrawer = TileDrawer(model)
        playerImage = ImageIO.read(File("src/main/resources/Block.png"))
    }

    override fun draw (g: Graphics) {
        g.color = Color.GRAY
        g.fillRect(0, 0, model.config.width, model.config.height)

        tileDrawer.draw(g)
        
        g.drawImage(
            playerImage,
            tileDrawer.getRenderLocX(model.gameModel.playerX, model.gameModel.playerY),
            tileDrawer.getRenderLocY(model.gameModel.playerX, model.gameModel.playerY) - model.config.gameScreenSettings.tileSize / 3,
            model.config.gameScreenSettings.tileSize,
            model.config.gameScreenSettings.tileSize,
            null
        )
    }

}