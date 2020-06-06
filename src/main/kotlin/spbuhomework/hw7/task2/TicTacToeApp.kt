package spbuhomework.hw7.task2

import javafx.stage.Stage
import spbuhomework.hw7.task2.views.MainMenu
import tornadofx.App
import tornadofx.launch

const val SIDE_LENGTH = 3
const val MENU_BUTTON_WIDTH = 400.0
const val GAME_FIELD_BUTTON_WIDTH = 100.0
const val BUTTON_HEIGHT = 100.0

class TicTacToeApp : App(MainMenu::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

fun main() {
    launch<TicTacToeApp>()
}
