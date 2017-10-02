package org.lwd.game.model
import business.Blockable
import org.itheima.kotlin.game.core.Painter
import org.lwd.game.Config

class Grass(override val x: Int, override val y: Int) :Blockable{
    //位置
//    override val x: Int=200
//    override val y: Int=200
    //宽高
    override val width: Int=Config.block
    override val height: Int=Config.block
    //显示 
    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }
}