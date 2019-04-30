package com.gloffr.model

import java.io.File
import java.io.PrintStream
import java.util.Scanner

class SaveStateController(private val model: Model) {

    fun saveState () {
        val file = File(model.config.saveLocation)
        val printer = PrintStream(file)
        model.config.levels.forEach {
            printer.println("${it.status} ${it.highScore}")
        }
        printer.close()
    }

    fun readState () {
        val file = File(model.config.saveLocation)
        if (!file.exists()) return
        val scanner = Scanner(file)
        var levelIndex = 0
        while (scanner.hasNextLine()) {
            val line = scanner.nextLine()
            val status = line.split(" ")[0]
            val highScore = line.split(" ")[1]
            if (status == "COMPLETE") {
                model.config.levels[levelIndex].status = LevelStatus.COMPLETE
                model.config.levels[levelIndex].highScore = highScore.toInt()
            } else if (status == "AVAILABLE") {
                model.config.levels[levelIndex].status = LevelStatus.AVAILABLE
                model.config.levels[levelIndex].highScore = highScore.toInt()
            } else if (status == "LOCKED") {
                model.config.levels[levelIndex].status = LevelStatus.LOCKED
                model.config.levels[levelIndex].highScore = highScore.toInt()
            } else {
                println("Value invalid $line")
            }
            levelIndex++
        }
        scanner.close()
    }

}