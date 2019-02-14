package com.gloffr.config

class Level {

    val name: String = "Untitled"
    val tiles: List<List<Int>> = emptyList()

    fun getWidth () : Int {
        return tiles[0].size
    }

    fun getHeight () : Int {
        return tiles.size
    }

}