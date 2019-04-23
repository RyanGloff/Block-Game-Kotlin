package com.gloffr.controller

import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.KeyListener
import java.awt.event.KeyEvent

import com.gloffr.Model

class GameScreenController(private val model: Model) : MouseListener, KeyListener {

    // MouseListener
    override fun mousePressed (e: MouseEvent) {
        
    }
    override fun mouseReleased (e: MouseEvent) {

    }
    override fun mouseClicked (e: MouseEvent) {

    }
    override fun mouseEntered (e: MouseEvent) {

    }
    override fun mouseExited (e: MouseEvent) {

    }

    // Key Listener
    override fun keyPressed (e: KeyEvent) {
        val level = model.gameModel.currentLevel
        val gm = model.gameModel
        if (level == null) {
            return
        }
        if (e.keyCode == KeyEvent.VK_W && level.locationExists(gm.playerX, gm.playerY - 1)) {
            model.gameModel.movePlayer(0, -1)
        } else if (e.keyCode == KeyEvent.VK_S && level.locationExists(gm.playerX, gm.playerY + 1)) {
            model.gameModel.movePlayer(0, 1)
        } else if (e.keyCode == KeyEvent.VK_A && level.locationExists(gm.playerX - 1, gm.playerY)) {
            model.gameModel.movePlayer(-1, 0)
        } else if (e.keyCode == KeyEvent.VK_D && level.locationExists(gm.playerX + 1, gm.playerY)) {
            model.gameModel.movePlayer(1, 0)
        }
    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

}