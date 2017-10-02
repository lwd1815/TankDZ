package model

import business.Blockable
import business.Movable
import com.sun.javafx.scene.traversal.Direction
import enums.DIrections
import org.itheima.kotlin.game.core.Painter
import org.lwd.game.Config
import org.lwd.game.model.View

class Tank(override var x:Int, override var y:Int):Movable{
    override val width: Int=Config.block
    override val height: Int=Config.block
    //方向
    override var currentDirection: DIrections=DIrections.UP
    //速度
    override val speed:Int=8
    
    private var badDIrection:DIrections?=null
    override fun draw() {
        //根据坦克的方向进行绘制
        //方式一
//        when(currentDirection){
//            DIrections.UP ->Painter.drawImage("img/tank_u.gif",x,y)
//            DIrections.DOWN ->Painter.drawImage("img/tank_d.gif",x,y)
//            DIrections.LEFT ->Painter.drawImage("img/tank_l.gif",x,y)
//            DIrections.RITHT ->Painter.drawImage("img/tank_r.gif",x,y)
//        }
        //方式二
        val imagepath:String = when (currentDirection) {
            DIrections.UP -> "img/tank_u.gif"
            DIrections.DOWN -> "img/tank_d.gif"
            DIrections.LEFT -> "img/tank_l.gif"
            DIrections.RITHT -> "img/tank_r.gif"
        }
        Painter.drawImage(imagepath,x,y)
    }
    
    fun move(direction: DIrections){
        //判断是否是望要碰撞的泛型概念股走
        if (direction==badDIrection){
            return
        }
        //当前的方向`和希望移动的方向不一致时,只做方向改变,不做移动
        if(this.currentDirection!=direction) {
            this.currentDirection = direction
            return
        }
        
        when(currentDirection){
            DIrections.UP -> y-=speed
            DIrections.DOWN -> y+=speed
            DIrections.LEFT -> x-=speed
            DIrections.RITHT -> x+=speed
        }
        //越界判断
        if(x<0)x=0
        if(x>Config.gameHeight-width)x=Config.gameWidth-width
        if(y<0)y=0
        if (y>Config.gameHeight-height)y=Config.gameHeight-height
    }
    override fun willCollision(block: Blockable): DIrections? {
        //未来坐标
        var x:Int=this.x
        var y:Int=this.y
        when(currentDirection){
            DIrections.UP -> y-=speed
            DIrections.DOWN -> y+=speed
            DIrections.LEFT -> x-=speed
            DIrections.RITHT -> x+=speed
        }
        //将要碰撞时做判断
        //TODO:检测下一步是否碰撞
        //如果阻挡物在碰撞物的上方 不碰撞 
        var collision:Boolean= when {
            block.y+block.height<=y -> false
        //如果阻挡物在运动物的下方  不碰撞   
            y+height<=block.y -> false
        //如果阻挡物在运动物的左方  不碰撞       
            block.x+block.width<=x -> false
        //如果阻挡物在运动物的右方  不碰撞       
            else -> x+width > block.x
        }
        //如果碰撞
        return if (collision) currentDirection else null
        return DIrections.UP
    }

    override fun notifyCollision(dIrections: DIrections?, block: Blockable?) {
        //TODO:接收碰撞信息
        this.badDIrection=dIrections
    }
}