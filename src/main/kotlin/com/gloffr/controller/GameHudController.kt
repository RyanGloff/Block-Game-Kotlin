package com.gloffr.controller

import java.awt.event.MouseListener
import java.awt.event.KeyListener
import java.awt.event.MouseEvent
import java.awt.event.KeyEvent

import com.gloffr.model.GameHudModel

class GameHudController (private val model: GameHudModel) : MouseListener, KeyListener {

    override fun mousePressed (e: MouseEvent) {
        if (e.getX() > model.backBtn.top && e.getX() < model.backBtn.top + model.backBtn.size &&
                e.getY() > model.backBtn.left && e.getY() < model.backBtn.left + model.backBtn.size) {
            model.returnToMenu()
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