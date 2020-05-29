package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.GameModel
import tornadofx.*

class WinScreen : View("TicTacToe") {
    override val root = vbox {
        label(GameModel.winnerMessage) {
            setPrefSize(400.0, 100.0)
        }.alignment = Pos.BASELINE_CENTER
        hbox {
            button("Back to main menu") {
                setPrefSize(400.0, 100.0)
                action {
                    find<GameField>().replaceWith<MainMenu>(sizeToScene = true)
                    close()
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }
}
