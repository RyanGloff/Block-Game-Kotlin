package com.gloffr.controller

import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import java.awt.event.MouseListener
import java.awt.event.MouseEvent

import com.gloffr.config.Config
import com.gloffr.model.Model
import com.gloffr.screen.MenuScreen
import com.gloffr.screen.GameScreen

class Controller(private val model: Model) : KeyListener, MouseListener {

    // Sub-controllers
    private val menuScreenController = MenuScreenController(model)
    private val gameScreenController = GameScreenController(model)

    // Key Events
    override fun keyPressed (e: KeyEvent) {
        if (model.activeScreen is GameScreen) {
            gameScreenController.keyPressed(e)
        }
    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

    // Mouse Events
    override fun mousePressed (e: MouseEvent) {
        if (model.activeScreen is MenuScreen) {
            menuScreenController.mousePressed(e)
        } else if (model.activeScreen is GameScreen) {
            gameScreenController.mousePressed(e)
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

}