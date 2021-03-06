package spbuhomework.hw8.task1.views

import javafx.geometry.Pos
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import spbuhomework.hw8.task1.players.HumanPlayer
import tornadofx.View
import tornadofx.vbox
import tornadofx.button
import tornadofx.action
import tornadofx.hbox
import tornadofx.label

class SideChoosing : View("TicTacToe") {
    override val root = vbox {
        label("Choose side") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.CENTER
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
