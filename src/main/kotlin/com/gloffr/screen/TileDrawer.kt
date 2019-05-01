package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

import com.gloffr.model.Model

class TileDrawer (var model: Model) {

    var tileSize = model.config.gameScreenSettings.tileSize
    val tileImg = ImageIO.read(File("src/main/resources/Block.png"))
    val activatedImg = ImageIO.read(File("src/main/resources/BlockActivated.png"))
    val teleportImg = ImageIO.read(File("src/main/resources/BlockTeleport.png"))

    fun draw (g: Graphics) {
        val level = model.gameModel.currentLevel
        if (level == null) return
        level.tiles.forEachIndexed { row, tileList ->
            tileList.forEachIndexed { col, tile ->
                if (level.locationExists(col, row)) {
                    val isTeleport = level.isTeleport(col, row)
                    if (model.gameModel.activated[row][col]) {
                        g.drawImage(activatedImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
                    } else if (isTeleport) {
                        g.drawImage(teleportImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
                    } else {
                        g.drawImage(tileImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
                    }
                }
            }
        }
    }

    fun getRenderLocX (x: Int, y: Int) : Int {
        val tiles = model.gameModel.currentLevel?.tiles
        if (tiles == null) return 0
        val rows = tiles.size
        return ((model.config.width - getWidth()) / 2) + ((rows - y - 1) + x) * tileSize / 2
    }
    fun getRenderLocY (x: Int, y: Int) : Int {
        val tiles = model.gameModel.currentLevel?.tiles
        if (tiles == null) return 0
        return ((model.config.height - getHeight()) / 2) + (y + x) * tileSize / 3
    }

    fun getWidth () : Int {
        val tiles = model.gameModel.currentLevel?.tiles
        if (tiles == null) return 0
        val rows = (tiles.size)
        val cols = (tiles.get(0).size)
        return (cols + rows) * tileSize / 2
    }
    fun getHeight () : Int {
        val tiles = model.gameModel.currentLevel?.tiles
        if (tiles == null) return 0
        val rows = (tiles.size)
        val cols = (tiles.get(0).size)
        val tileTopHeight = tileSize * 2 / 3
        return (rows + cols) * tileSize / 3 + 2 * tileSize / 3
    }

}