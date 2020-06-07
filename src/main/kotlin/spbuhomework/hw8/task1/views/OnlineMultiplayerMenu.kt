package spbuhomework.hw8.task1.views

import io.ktor.util.KtorExperimentalAPI
import javafx.application.Platform
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import kotlinx.coroutines.runBlocking
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import spbuhomework.hw8.task1.players.HumanPlayer
import spbuhomework.hw8.task1.players.OnlinePlayer
import tornadofx.*
import java.lang.Thread.sleep

class OnlineMultiplayerMenu : View("TicTacToe") {
    private val loadingText = SimpleStringProperty("Trying to connect...")
    private var isLoaded = false

    override val root = vbox {
        label(loadingText) {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.CENTER
        button("Play") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
            action {
                if (isLoaded) {
                    this@OnlineMultiplayerMenu.replaceWith<GameField>(sizeToScene = true)
                }
            }
        }
    }

    @KtorExperimentalAPI
    override fun onDock() {
        super.onDock()
        runAsync {
            var newLoadingText = "Connected! Your sign = "
            (GameModel.opponent as OnlinePlayer).setConnection()
            while (GameModel.opponent.playerChar == ' ') {
                sleep(10)
            }
            if (GameModel.opponent.playerChar == 'X') {
                GameModel.firstPlayer = GameModel.opponent
                GameModel.secondPlayer = HumanPlayer()
                newLoadingText += "O"
            } else {
                GameModel.firstPlayer = HumanPlayer()
                GameModel.secondPlayer = GameModel.opponent
                newLoadingText += "X"
            }
            return@runAsync newLoadingText
        } ui {
            loadingText.value = it
            isLoaded = true
        }
    }
}
