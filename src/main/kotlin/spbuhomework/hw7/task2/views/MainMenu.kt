package spbuhomework.hw7.task2.views

import spbuhomework.hw7.task2.BUTTON_HEIGHT
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.MENU_BUTTON_WIDTH
import spbuhomework.hw7.task2.players.HumanPlayer
import tornadofx.View
import tornadofx.button
import tornadofx.vbox
import tornadofx.action

class MainMenu : View("TicTacToe") {
    override val root = vbox {
        button("Singleplayer") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
            action {
                this@MainMenu.replaceWith<BotDifficultyChoosing>()
            }
        }
        button("Local Multiplayer") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
            action {
                GameModel.firstPlayer = HumanPlayer()
                GameModel.secondPlayer = HumanPlayer()
                this@MainMenu.replaceWith<GameField>(sizeToScene = true)
            }
        }
    }
}
