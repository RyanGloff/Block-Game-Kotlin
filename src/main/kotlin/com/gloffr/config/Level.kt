package com.gloffr.config

import com.gloffr.model.LevelStatus
import com.gloffr.model.GameModel

class Level {

    var tiles: List<List<Int>> = emptyList()
    var startX: Int = 0
    var startY: Int = 0
    var status: LevelStatus = LevelStatus.LOCKED
    var highScore: Int = Int.MAX_VALUE
    var specialized: List<SpecializedBlock> = listOf()

    fun getWidth () : Int {
        return tiles[0].size
    }

    fun getHeight () : Int {
        return tiles.size
    }

    fun locationExists (x: Int, y: Int) : Boolean {
        if (y < 0 || y >= tiles.size || x < 0 || x >= tiles.get(y).size) {
            return false
        }
        return tiles.get(y).get(x) != 0
    }

    fun postProcessMove(gameModel: GameModel) {
        gameModel.activated[gameModel.playerY][gameModel.playerX] = !gameModel.activated[gameModel.playerY][gameModel.playerX]
        specialized.forEach { spec ->
            val meta = spec.meta
            if (spec.type == "teleport") {
                if (gameModel.playerX == meta.get("x1") && gameModel.playerY == meta.get("y1")) {
                    gameModel.playerX = meta.get("x2")!!
                    gameModel.playerY = meta.get("y2")!!
                    gameModel.activated[gameModel.playerY][gameModel.playerX] = !gameModel.activated[gameModel.playerY][gameModel.playerX]
                }
            }
        }
        if (gameModel.levelComplete()) {
            gameModel.finishLevel()
        }
    }

}