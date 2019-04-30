package com.gloffr.controller

import java.awt.event.MouseListener
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.KeyEvent

import com.gloffr.model.GameHudModel

class GameHudController (private val model: GameHudModel) : MouseListener, KeyListener {

    override fun mousePressed (e: MouseEvent) {
        if (model.backBtn.contains(e.getX(), e.getY())) {
            model.returnToMenu()
        } else if (model.resetBtn.contains(e.getX(), e.getY())) {
            model.resetLevel()
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
    
    override fun keyPressed (e: KeyEvent) {

    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

}