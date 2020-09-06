package spbuhomework.hw8.task1.views

import javafx.geometry.Pos
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import tornadofx.View
import tornadofx.label
import tornadofx.vbox
import tornadofx.button
import tornadofx.hbox
import tornadofx.action

class GameOverScreen : View("TicTacToe") {
    override val root = vbox {
        label(GameModel.winnerMessage) {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.BASELINE_CENTER
        hbox {
            button("Back to main menu") {
                setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
                action {
                    find<GameField>().replaceWith<MainMenu>(sizeToScene = true)
                    GameModel.gameOver = false
                    close()
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }
}
