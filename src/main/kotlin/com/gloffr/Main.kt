package com.gloffr

import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper

import com.gloffr.config.Config
import com.gloffr.controller.Controller
import com.gloffr.model.Model
import com.gloffr.screen.View

fun main (args: Array<String>) {
    if (args.size != 1) {
        println("Invalid args")
        System.exit(1)
    }

    val configFile = File(args[0])
    val configStr = configFile.readLines().joinToString(separator = "")
    val objMapper = ObjectMapper()
    val config = objMapper.readValue(configStr, Config::class.java);

    val model = Model(config, true)
    val controller = Controller(model)
    View(model, controller)
}