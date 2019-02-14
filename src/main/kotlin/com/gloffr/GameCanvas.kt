package com.gloffr

import java.awt.Canvas
import java.awt.Dimension

class GameCanvas(val w: Int, val h: Int) : Canvas() {

    override fun getPreferredSize(): Dimension {
        return Dimension(w, h)
    }

}