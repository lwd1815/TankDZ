package org.lwd.game.model

import business.Blockable
import org.lwd.game.Config
import org.itheima.kotlin.game.core.Painter
//通过构造函数传递位置参数
/**
 * 具备阻塞能力
 */
class Wall(override val x: Int, override val y: Int):Blockable{
    //位置
//    override val x: Int=100
//    override val y: Int=100
     //宽高
    override val width: Int=Config.block
    override val height: Int=Config.block
     //显示 
    override fun draw() {
        Painter.drawImage("img/wall.gif",x,y)
    }
}