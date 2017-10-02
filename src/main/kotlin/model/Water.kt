package org.lwd.game.model

import business.Blockable
import org.itheima.kotlin.game.core.Painter
import org.lwd.game.Config

class Water(override val x: Int, override val y: Int) : Blockable {
    //位置
//    override val x: Int=400
//    override val y: Int=400
    //宽高
    override val width: Int= Config.block
    override val height: Int= Config.block
    //显示 
    override fun draw() {
        Painter.drawImage("img/water.gif", x, y)
    }
}