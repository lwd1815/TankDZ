package business

import enums.DIrections
import org.lwd.game.model.View

//运动的能力
//接口可以继承接口
interface Movable:View {
    
    //可移动的物体存在方向和速度
    val currentDirection:DIrections
    val speed:Int
    /**
     * 判断移动的物体是否和对应的阻塞物体发生碰撞
     * return ture 碰撞 false 没有碰撞
     * 返回要碰撞的方向,如果是null说明没有碰撞
     */
    fun willCollision(block:Blockable):DIrections?
    //通知碰撞
    fun notifyCollision(dIrections: DIrections?,block: Blockable?)
}