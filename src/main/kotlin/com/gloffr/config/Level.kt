package com.gloffr.config

import com.gloffr.LevelStatus

class Level {

    val tiles: List<List<Int>> = emptyList()
    val startX: Int = 0
    val startY: Int = 0
    var status: LevelStatus = LevelStatus.LOCKED
    var specialized: List<SpecializedBlock> = listOf()

    fun getWidth () : Int {
        return tiles[0].size
    }

    fun getHeight () : Int {
        return tiles.size
    }

}