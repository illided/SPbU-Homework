package spbuhomework.hw8.task1.views

import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import spbuhomework.hw8.task1.players.HumanPlayer
import spbuhomework.hw8.task1.players.OnlinePlayer
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
        button("Online Multiplayer") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
            action {
                GameModel.opponent = OnlinePlayer()
                this@MainMenu.replaceWith<OnlineMultiplayerMenu>(sizeToScene = true)
            }
        }
    }
}
