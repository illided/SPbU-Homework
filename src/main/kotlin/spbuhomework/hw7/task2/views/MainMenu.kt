package spbuhomework.hw7.task2.views

import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.players.HumanPlayer
import tornadofx.*

class MainMenu : View("TicTacToe") {
    override val root = vbox{
        button("Singleplayer") {
            setPrefSize(400.0, 100.0)
            action{
                this@MainMenu.replaceWith<BotDifficultyChoosing>()
            }
        }
        button("Local Multiplayer"){
            setPrefSize(400.0, 100.0)
            action{
                GameModel.firstPlayer = HumanPlayer()
                GameModel.secondPlayer = HumanPlayer()
                this@MainMenu.replaceWith<GameField>(sizeToScene = true)
            }
        }
    }
}
