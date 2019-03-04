package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color

import com.gloffr.Model
import com.gloffr.config.Level
import com.gloffr.graphics.SpriteSheet
import com.gloffr.ObjectRenderInfo
import com.gloffr.BlockRenderInfo
import com.gloffr.Block

class GameScreen(model: Model) : Screen(model) {

    var currentLevel: Level? = null
    val blockSS: SpriteSheet
    val tileSize: Int
    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0
    var cols: Int = 0
    var rows: Int = 0
    val blockRenderLocs: MutableList<BlockRenderInfo> = mutableListOf<BlockRenderInfo>()
    val backBtn = ObjectRenderInfo(25, 25, 50)
    val navBtnSS: SpriteSheet
    var playerX: Int = 0
    var playerY: Int = 0
    var playerRenderInfo: ObjectRenderInfo
    val blocks: MutableList<MutableList<Block>> = mutableListOf<MutableList<Block>>()

    init {
        blockSS = SpriteSheet("src/main/resources/BlocksSheet.png", 5, 1)
        navBtnSS = SpriteSheet("src/main/resources/MenuScreenButtons.png", 2, 1)
        tileSize = model.config.gameScreenSettings.tileSize

        playerRenderInfo = ObjectRenderInfo(0, 0, tileSize);
    }

    override fun draw (g: Graphics) {
        g.color = Color(50, 50, 50)
        g.fillRect(0, 0, model.config.width, model.config.height)

        drawBlocks(g)

        drawPlayer(g)

        // Back button
        g.drawImage(navBtnSS.getSprite(0, 0), backBtn.left, backBtn.top, backBtn.size, backBtn.size, null)
    }

    fun loadLevel (level: Level) {
        currentLevel = level
        rows = level.tiles.size
        cols = level.tiles[0].size
        height = (cols + rows + 1) * tileSize / 3
        width = (cols + rows) * tileSize / 2
        x = (model.config.width - width) / 2
        y = (model.config.height - height) / 2

        blockRenderLocs.clear()
        blocks.clear()
        level.tiles.forEachIndexed { rIndex, row ->
            blocks.add(mutableListOf<Block>()) 
            row.forEachIndexed { cIndex, tile ->
                blocks[rIndex].add(Block(tile))  
                if (tile != 0) {
                    blockRenderLocs.add(BlockRenderInfo(blocks[rIndex][cIndex], x + (cIndex * tileSize - rIndex * tileSize + (level.tiles.size - 1) * tileSize) / 2, y + (cIndex + rIndex) * tileSize / 3, tileSize))
                }
            }
        }
        level.specialized.forEach{ specialized ->
            val type = specialized.type
            if (type == "teleport") {
                val x1 = specialized.meta.get("x1")!!
                val y1 = specialized.meta.get("y1")!!
                val x2 = specialized.meta.get("x2")!!
                val y2 = specialized.meta.get("y2")!!
                blocks[y1][x1].teleport = true
                blocks[y1][x1].teleportToX = x2
                blocks[y1][x1].teleportToY = y2
            }
            
        }
        updatePlayerLocation(level.startX, level.startY)
        blocks[playerY][playerX].activated = true
    }

    private fun updatePlayerLocation (newX: Int, newY: Int) {
        playerX = newX
        playerY = newY
        playerRenderInfo.left = this.x + (playerX * tileSize - playerY * tileSize + (rows - 1) * tileSize) / 2
        playerRenderInfo.top = this.y + (playerX + playerY) * tileSize / 3 - tileSize / 3
    }

    fun movePlayerUp () = movePlayerTo(playerX, playerY - 1)
    fun movePlayerDown () = movePlayerTo(playerX, playerY + 1)
    fun movePlayerLeft () = movePlayerTo(playerX - 1, playerY)
    fun movePlayerRight () = movePlayerTo(playerX + 1, playerY)

    private fun movePlayerTo(x: Int, y: Int) {
        val tiles = currentLevel?.tiles!!
        if (x < tiles[0].size && x >= 0 && y < tiles.size && y >= 0 && tiles[y][x] != 0) {
            updatePlayerLocation(x, y)
            blocks[playerY][playerX].activated = !blocks[playerY][playerX].activated
            if (isFinished()) {
                model.setLevelComplete(currentLevel!!)
                model.switchToMenu()
            } else if (blocks[playerY][playerX].teleport) {
                val block = blocks[playerY][playerX]
                movePlayerTo(block.teleportToX, block.teleportToY)
            }
        }
    }

    private fun animateMovePlayerTo(x: Int, y: Int) {
        
    }

    private fun isFinished () : Boolean {
        return blocks.all{ 
            it.all{
                it.activated || it.value == 0
            }
        }
    }

    private fun drawBlocks(g: Graphics) {
        blockRenderLocs.forEach { block -> 
            g.drawImage(blockSS.getSprite(block.block.value - 1 + if (block.block.activated) { 1 } else { 0 }, 0), block.left, block.top, block.size, block.size, null)
        }
    }

    private fun drawPlayer(g: Graphics) {
        g.color = Color.RED
        g.drawImage(blockSS.getSprite(3, 0), playerRenderInfo.left, playerRenderInfo.top, playerRenderInfo.size, playerRenderInfo.size, null)
    }

}