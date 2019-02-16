package com.gloffr.controller

import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.KeyListener
import java.awt.event.KeyEvent

import com.gloffr.Model

class GameScreenController(private val model: Model) : MouseListener, KeyListener {

    // MouseListener
    override fun mousePressed (e: MouseEvent) {
        if (model.gameScreen.backBtn.contains(e.x, e.y)) {
            model.switchToMenu()
        }
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
        if (e.keyCode == KeyEvent.VK_W) {
            model.gameScreen.movePlayerUp()
        } else if (e.keyCode == KeyEvent.VK_S) {
            model.gameScreen.movePlayerDown()
        } else if (e.keyCode == KeyEvent.VK_A) {
            model.gameScreen.movePlayerLeft()
        } else if (e.keyCode == KeyEvent.VK_D) {
            model.gameScreen.movePlayerRight()
        }
    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

}