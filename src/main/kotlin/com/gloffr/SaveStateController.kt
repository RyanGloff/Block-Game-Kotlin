package com.gloffr

import java.io.File
import java.io.PrintStream
import java.util.Scanner

class SaveStateController(private val model: Model) {

    fun saveState () {
        val file = File(model.config.saveLocation)
        val printer = PrintStream(file)
        model.config.levels.forEach {
            printer.println("${it.status}")
        }
        printer.close()
    }

    fun readState () {
        val file = File(model.config.saveLocation)
        val scanner = Scanner(file)
        var levelIndex = 0
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            println("line: $line")
            if (line == "COMPLETE") {
                model.config.levels[levelIndex++].status = LevelStatus.COMPLETE
            } else if (line == "AVAILABLE") {
                model.config.levels[levelIndex++].status = LevelStatus.AVAILABLE
            } else if (line == "LOCKED") {
                model.config.levels[levelIndex++].status = LevelStatus.LOCKED
            } else {
                println("Value invalid $line")
            }
        }
        model.config.levels.forEach{ 
            println("${it.status}")
        }
        scanner.close()
    }

}