package com.gloffr.model

import com.gloffr.config.Level

class LevelMenuRenderInfo (val level: Level, val page: Int, left: Int, top: Int, size: Int) : ObjectRenderInfo(left, top, size) {}