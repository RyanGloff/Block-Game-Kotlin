package com.gloffr.controller

import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.KeyListener
import java.awt.event.KeyEvent

import com.gloffr.Model

class MenuScreenController(private val model: Model) : MouseListener, KeyListener {

    override fun keyPressed (e: KeyEvent) {

    }
    override fun keyReleased (e: KeyEvent) {

    }
    override fun keyTyped (e: KeyEvent) {

    }

    override fun mousePressed (e: MouseEvent) {
        val menuScreen = model.menuScreen
        val pageBackBtn = menuScreen.pageBackBtnInfo
        val pageForwardBtn = menuScreen.pageForwardBtnInfo
        if (pageBackBtn.contains(e.x, e.y)) {
            menuScreen.pageBack()
        }
        if (pageForwardBtn.contains(e.x, e.y)) {
            menuScreen.pageForward()
        }
        val levelSelectBtns = model.menuScreen.levelRenderInfo
        val currentPage = model.menuScreen.currentPage
        levelSelectBtns.forEach { level ->
            if (level.page == currentPage && level.contains(e.x, e.y)) {
                model.startGame(level.level)
            }
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