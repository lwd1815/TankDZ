import business.Blockable
import business.Movable
import enums.DIrections
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyCodeCombination
import javafx.scene.input.KeyEvent
import model.Tank
import org.itheima.kotlin.game.core.Window
import org.lwd.game.Config
import org.lwd.game.model.*
import java.io.File

class gameWindows:Window(title = "坦克大战",icon = "img/logo.jpg",
        width = Config.gameWidth
        ,height = Config.gameHeight
        ){
    private val views:ArrayList<View> = arrayListOf<View>()
    //晚点创建爱你
    private lateinit var tank :Tank
    override fun onCreate() {

        val file = File(javaClass.getResource("/map/1.map").path)
        //读取文件当中的行
        val lines: List<String> = file.readLines()
        var lineNum=0
        lines.forEach{line->
            var columnNum=0
            line.toCharArray().forEach { column->
                when(column){
                    '砖'->views.add(Wall(columnNum*Config.block,lineNum*Config.block))
                    '铁'->views.add(Steel(columnNum*Config.block,lineNum*Config.block))
                    '草'->views.add(Grass(columnNum*Config.block,lineNum*Config.block))
                    '水'->views.add(Water(columnNum*Config.block,lineNum*Config.block))
                }
                columnNum++
            }
            lineNum++
        }
        //添加我方的坦克
        tank= Tank(Config.block * 10, Config.block * 12)
        views.add(tank)
    }

    override fun onDisplay() {
        views.forEach{
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        //用户操作时
        println("1111111")
        when(event.code){
            KeyCode.W->{tank.move(DIrections.UP)}
            KeyCode.S->{tank.move(DIrections.DOWN)}
            KeyCode.A->{tank.move(DIrections.LEFT)}
            KeyCode.D->{tank.move(DIrections.RITHT)}
        }
    }

    override fun onRefresh() {
        //做业务耗时的逻辑,判断运动的物体和阻塞物体是否发生碰撞
        //1,找到运动的物体
        views.filter { it is Movable }.forEach{move->
            //2.找到阻塞的物体
            move as Movable
            var badDirection:DIrections?=null
            var badBlock:Blockable?=null
            views.filter { it is Blockable }.forEach blockTag@{block->
                //3,根据运动物体和阻塞物体的方位做一些判断,遍历集合找到是否发生碰撞
                block as Blockable
                val willCollision :DIrections?= move.willCollision(block)
                //.let 表示不为空,为空时不走此方法
                willCollision?.let{
                    badDirection=willCollision
                    badBlock=block
                    //移动的发现碰撞,跳出当前循环
                    return@blockTag
                }
            }
            //找到和move碰撞的阻塞快和找到会碰撞的方向
            //通知可以移动的物体会在那个方向和那个物体碰撞
            move.notifyCollision(badDirection,badBlock)
        }
    }
}


