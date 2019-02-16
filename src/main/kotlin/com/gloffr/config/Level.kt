package com.gloffr.config

class Level {

    val tiles: List<List<Int>> = emptyList()
    var locked: Boolean = true
    val startX: Int = 0
    val startY: Int = 0
    var complete: Boolean = false
    var specialized: List<SpecializedBlock> = listOf()

    fun getWidth () : Int {
        return tiles[0].size
    }

    fun getHeight () : Int {
        return tiles.size
    }

}