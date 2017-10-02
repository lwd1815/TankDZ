package org.lwd.game.model

import business.Blockable
import org.itheima.kotlin.game.core.Painter
import org.lwd.game.Config

class Steel(override val x: Int, override val y: Int) : Blockable {
    //位置
//    override val x: Int=300
//    override val y: Int=300
    //宽高
    override val width: Int= Config.block
    override val height: Int= Config.block
    //显示 
    override fun draw() {
        Painter.drawImage("img/steel.gif", x, y)
    }
}