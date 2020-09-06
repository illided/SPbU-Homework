package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.BUTTON_HEIGHT
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.MENU_BUTTON_WIDTH
import spbuhomework.hw7.task2.players.HumanPlayer
import tornadofx.View
import tornadofx.stackpane
import tornadofx.label
import tornadofx.hbox
import tornadofx.button
import tornadofx.action

class SideChoosing : View("TicTacToe") {
    override val root = stackpane {
        label("Choose side") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.TOP_CENTER
        hbox {
            button("X") {
                setPrefSize(MENU_BUTTON_WIDTH / 2, BUTTON_HEIGHT)
                action {
                    GameModel.firstPlayer = HumanPlayer()
                    GameModel.secondPlayer = GameModel.opponent
                    this@SideChoosing.replaceWith<GameField>(sizeToScene = true)
                }
            }
            button("O") {
                setPrefSize(MENU_BUTTON_WIDTH / 2, BUTTON_HEIGHT)
                action {
                    GameModel.firstPlayer = GameModel.opponent
                    GameModel.secondPlayer = HumanPlayer()
                    this@SideChoosing.replaceWith<GameField>(sizeToScene = true)
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }
}
