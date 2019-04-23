package com.gloffr.screen

import javax.swing.JFrame
import java.awt.Canvas
import java.lang.Thread
import java.awt.Color
import java.awt.Dimension
import java.awt.RenderingHints
import java.awt.Graphics2D

import com.gloffr.config.Config
import com.gloffr.controller.Controller
import com.gloffr.model.Model

class View(val model: Model, val controller: Controller) {

    val frame: JFrame
    val canvas: GameCanvas
    val thread: Thread
    var running = false

    init {
        canvas = GameCanvas(model.config.width, model.config.height)
        
        frame = JFrame()
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(canvas)
        frame.pack()
        frame.isResizable = false
        frame.setLocationRelativeTo(null)
        frame.isVisible = true

        canvas.addKeyListener(controller)
        canvas.addMouseListener(controller)
        canvas.requestFocus()
        
        thread = Thread {
            val fps = 30
            val tpf = 1000000000 / fps
            var startTime = System.nanoTime()
            while (running) {
                if (System.nanoTime() - startTime > tpf) {
                    render()
                    startTime += tpf
                }
            }
        }
        start()
    }

    @Synchronized fun start () {
        if (running) return
        thread.start()
        running = true
    }

    @Synchronized fun stop () {
        if (!running) return
        thread.join()
        running = false
    }

    fun render () {
        val bs = canvas.getBufferStrategy()
        if (bs == null) {
            canvas.createBufferStrategy(3)
            return
        }
        val g = bs.getDrawGraphics()
        
        model.activeScreen.draw(g)

        g.dispose()
        bs.show()
    }

}