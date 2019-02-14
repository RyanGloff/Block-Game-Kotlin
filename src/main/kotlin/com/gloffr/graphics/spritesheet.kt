package com.gloffr.graphics

import java.awt.Canvas
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File

class SpriteSheet(path: String, private val cols: Int, private val rows: Int) {
    private val raw: BufferedImage
    private val sprites: Array<BufferedImage>

    init {
        raw = ImageIO.read(File(path))
        val spriteWidth = raw.getWidth() / cols
        val spriteHeight = raw.getHeight() / rows
        sprites = Array(rows * cols, { i -> 
            val left = (i % cols) * spriteWidth
            val top = (i / cols) *  spriteHeight
            raw.getSubimage(left, top, spriteWidth, spriteHeight)
        })
    }

    fun getSprite (x: Int, y: Int) : BufferedImage {
        return sprites[x + y * cols]
    }
}