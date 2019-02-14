package com.gloffr.screen

import java.awt.Graphics
import java.awt.Color

import com.gloffr.config.Config
import com.gloffr.config.MenuScreenSettings
import com.gloffr.config.Level
import com.gloffr.graphics.SpriteSheet
import com.gloffr.Model
import com.gloffr.LevelMenuRenderInfo
import com.gloffr.ObjectRenderInfo

class MenuScreen(model: Model) : Screen(model) {

    val levelRenderInfo: MutableList<LevelMenuRenderInfo> = mutableListOf<LevelMenuRenderInfo>()
    var currentPage: Int = 0
    val totalPages: Int

    val pageBackBtnInfo: ObjectRenderInfo
    val pageForwardBtnInfo: ObjectRenderInfo

    val buttonSS: SpriteSheet

    init {
        // Config refs
        val blockSize = model.config.menuScreenSettings.blockSize
        val interblockMargin = model.config.menuScreenSettings.interblockMargin
        val minMargin = model.config.menuScreenSettings.minMargin
        val navBtnSize = model.config.menuScreenSettings.navBtnSize
        val navToSelectorMargin = model.config.menuScreenSettings.navToSelectorMargin
        
        val selectorWidth = model.config.width - (navToSelectorMargin + navBtnSize) * 2
        val selectorHeight = model.config.height
        val rows = (selectorHeight - minMargin) / (blockSize + interblockMargin)
        val cols = (selectorWidth - minMargin) / (blockSize + interblockMargin)
        val horizontalMargin = (selectorWidth - cols * blockSize - (cols - 1) * interblockMargin) / 2
        val verticalMargin = (selectorHeight - rows * blockSize - (rows - 1) * interblockMargin) / 2
        totalPages = model.config.levels.size / (rows * cols) + 1

        model.config.levels.forEachIndexed{ index, level -> 
            val col = index % cols
            val row = index / cols
            val page = index / (rows * cols)
            val left = navBtnSize + navToSelectorMargin + horizontalMargin + col * (blockSize + interblockMargin)
            val top = verticalMargin + (row - page * rows) * (blockSize + interblockMargin)

            levelRenderInfo.add(LevelMenuRenderInfo(level, page, left, top, blockSize))
        }

        pageBackBtnInfo = ObjectRenderInfo(horizontalMargin, (model.config.height - navBtnSize) / 2, navBtnSize)
        pageForwardBtnInfo = ObjectRenderInfo(model.config.width - horizontalMargin - navBtnSize, (model.config.height - navBtnSize) / 2, navBtnSize)

        buttonSS = SpriteSheet("src/main/resources/MenuScreenButtons.jpg", 2, 1)
    }

    override fun draw (g: Graphics) {
        g.color = Color.GRAY
        g.fillRect(0, 0, model.config.width, model.config.width)

        g.color = Color.GREEN
        levelRenderInfo.forEach{ info ->
            if (info.page == currentPage)
                g.fillRect(info.left, info.top, info.size, info.size)
        }

        g.drawImage(buttonSS.getSprite(0, 0), pageBackBtnInfo.left, pageBackBtnInfo.top, pageBackBtnInfo.size, pageBackBtnInfo.size, null)
        g.drawImage(buttonSS.getSprite(1, 0), pageForwardBtnInfo.left, pageForwardBtnInfo.top, pageForwardBtnInfo.size, pageForwardBtnInfo.size, null)
    }

    fun pageBack () {
        if (currentPage == 0) return
        currentPage--
    }

    fun pageForward () {
        if (currentPage == totalPages - 1) return
        currentPage++
    }

}