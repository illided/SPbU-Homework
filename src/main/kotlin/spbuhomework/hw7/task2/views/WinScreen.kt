package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.BUTTON_HEIGHT
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.MENU_BUTTON_WIDTH
import tornadofx.View
import tornadofx.label
import tornadofx.vbox
import tornadofx.button
import tornadofx.hbox
import tornadofx.action

class WinScreen : View("TicTacToe") {
    override val root = vbox {
        label(GameModel.winnerMessage) {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.BASELINE_CENTER
        hbox {
            button("Back to main menu") {
                setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
                action {
                    find<GameField>().replaceWith<MainMenu>(sizeToScene = true)
                    close()
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }
}
