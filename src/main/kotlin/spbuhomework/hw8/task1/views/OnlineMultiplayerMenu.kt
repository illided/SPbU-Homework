package spbuhomework.hw8.task1.views

import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import spbuhomework.hw8.task1.players.HumanPlayer
import spbuhomework.hw8.task1.players.OnlinePlayer
import tornadofx.*
import java.lang.Thread.sleep

class OnlineMultiplayerMenu : View("TicTacToe") {
    override val root = vbox {
        label("Trying to connect...") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }
    }

    @KtorExperimentalAPI
    override fun onDock() {
        super.onDock()
        runBlocking {
            (GameModel.opponent as OnlinePlayer).setConnection()
            while (GameModel.opponent.playerChar == ' ') {
                sleep(10)
            }
        }
        if (GameModel.opponent.playerChar == 'X') {
            GameModel.firstPlayer = GameModel.opponent
            GameModel.secondPlayer = HumanPlayer()
        } else {
            GameModel.firstPlayer = HumanPlayer()
            GameModel.secondPlayer = GameModel.opponent
        }
        this.replaceWith<GameField>(sizeToScene = true)
    }

}
