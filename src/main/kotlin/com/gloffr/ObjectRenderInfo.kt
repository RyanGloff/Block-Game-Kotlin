package com.gloffr

class ObjectRenderInfo(val left: Int, val top: Int, val size: Int) {

    fun contains (x: Int, y: Int) : Boolean {
        return x >= left && x <= left + size && y >= top && y <= top + size
    }

}