package com.gloffr.screen

import java.awt.Graphics

import com.gloffr.model.Model

abstract class Screen (val model: Model) {

    abstract fun draw (g: Graphics)

}