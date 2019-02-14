package com.gloffr.config

class Level {

    val tiles: List<List<Int>> = emptyList()
    var locked: Boolean = true

    fun getWidth () : Int {
        return tiles[0].size
    }

    fun getHeight () : Int {
        return tiles.size
    }

}