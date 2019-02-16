package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color

import com.gloffr.Model
import com.gloffr.config.Level
import com.gloffr.graphics.SpriteSheet

class GameScreen(model: Model) : Screen(model) {

    var currentLevel: Level? = null
    val blockSS: SpriteSheet
    val tileSize: Int
    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0

    init {
        blockSS = SpriteSheet("src/main/resources/BlocksSheet.png", 5, 1)
        tileSize = model.config.gameScreenSettings.tileSize
    }

    override fun draw (g: Graphics) {
        g.color = Color(50, 50, 50)
        g.fillRect(0, 0, model.config.width, model.config.height)

        drawBlocks(g)
    }

    fun loadLevel (level: Level) {
        currentLevel = level
        val rows = level.tiles.size
        val cols = level.tiles[0].size
        height = (cols + rows + 1) * tileSize / 3
        width = (cols + rows) * tileSize / 2
        x = (model.config.width - width) / 2
        y = (model.config.height - height) / 2
    }

    private fun drawBlocks(g: Graphics) {
        currentLevel?.tiles!!.forEachIndexed { rIndex, row -> 
            row.forEachIndexed { cIndex, tile -> 
                if (tile != 0)
                    g.drawImage(blockSS.getSprite(tile - 1, 0), x + (cIndex * tileSize - rIndex * tileSize + (currentLevel?.tiles!!.size - 1) * tileSize) / 2, y + (cIndex + rIndex) * tileSize / 3, tileSize, tileSize, null)
            }
        }
    }

}