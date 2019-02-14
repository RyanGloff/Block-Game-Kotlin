package com.gloffr.controller

import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import java.awt.event.MouseListener
import java.awt.event.MouseEvent

import com.gloffr.config.Config
import com.gloffr.Model
import com.gloffr.screen.MenuScreen

class Controller(private val model: Model) : KeyListener, MouseListener {

    // Sub-controllers
    private val menuScreenController = MenuScreenController(model)

    // Key Events
    override fun keyPressed (e: KeyEvent) {

    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

    // Mouse Events
    override fun mousePressed (e: MouseEvent) {
        if (model.activeScreen is MenuScreen)
        menuScreenController.mousePressed(e)
    }
    override fun mouseReleased (e: MouseEvent) {

    }
    override fun mouseClicked (e: MouseEvent) {

    }
    override fun mouseEntered (e: MouseEvent) {

    }
    override fun mouseExited (e: MouseEvent) {

    }

}