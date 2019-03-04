package com.gloffr

open class ObjectRenderInfo(var left: Int, var top: Int, val size: Int) {

    fun contains (x: Int, y: Int) : Boolean {
        return x >= left && x <= left + size && y >= top && y <= top + size
    }

}