package spbuhomework.hw7.task2

import javafx.stage.Stage
import spbuhomework.hw7.task2.views.MainMenu
import tornadofx.App
import tornadofx.launch

const val SIDE_LENGTH = 3

class TicTacToeApp: App(MainMenu::class){
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

fun main(){
    launch<TicTacToeApp>()
}