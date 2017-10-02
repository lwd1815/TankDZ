package org.lwd.game.model

import org.itheima.kotlin.game.core.Painter
import org.lwd.game.Config

//显示的视图,定义显示规范
interface View {
    //定义属性,让实现类去实现
    //位置
    val x:Int
    val y:Int
    // 宽高l
    val width:Int
    val height:Int
    // 显示
    fun draw()
}