package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color
import javax.imageio.ImageIO
import java.io.File

import com.gloffr.model.Model
import com.gloffr.graphics.SpriteSheet

class TileDrawer (var model: Model) {

    var tileSize = model.config.gameScreenSettings.tileSize
    var blockSheet = SpriteSheet("src/main/resources/BlocksSheet.png", 5, 1)
    val tileImg = blockSheet.getSprite(0, 0)
    val activatedImg = blockSheet.getSprite(1, 0)
    val teleportImg = blockSheet.getSprite(2, 0)
    val endTeleportImg = blockSheet.getSprite(3, 0)

    fun draw (g: Graphics) {
        val level = model.gameModel.currentLevel
        if (level == null) return
        level.tiles.forEachIndexed { row, tileList ->
            tileList.forEachIndexed { col, tile ->
                if (level.locationExists(col, row)) {
                    val isTeleport = level.isTeleport(col, row)
                    val isEndTeleport = level.isEndTeleport(col, row)
                    if (model.gameModel.activated[row][col]) {
                        g.drawImage(activatedImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
                    } else if (isTeleport) {
                        g.drawImage(teleportImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
                    } else if (isEndTeleport) {
                        g.drawImage(endTeleportImg, getRenderLocX(col, row), getRenderLocY(col, row), tileSize, tileSize, null)
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
        return (rows + cols) * tileSize / 3 + 2 * tileSize / 3
    }

}