package spbuhomework.hw8.task1.views

import io.ktor.util.KtorExperimentalAPI
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.MENU_BUTTON_WIDTH
import spbuhomework.hw8.task1.players.HumanPlayer
import spbuhomework.hw8.task1.players.OnlinePlayer
import tornadofx.View
import tornadofx.label
import tornadofx.button
import tornadofx.vbox
import tornadofx.action
import java.lang.Thread.sleep

private const val SLEEP_TIME_FOR_LOADING = 10.toLong()

class OnlineMultiplayerMenu : View("TicTacToe") {
    private val loadingText = SimpleStringProperty()
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
        loadingText.value = "Trying to connect..."
        super.onDock()
        runAsync {
            var newLoadingText = "Connected! Your sign = "
            (GameModel.opponent as OnlinePlayer).setConnection()
            while (GameModel.opponent.playerChar == ' ') {
                sleep(SLEEP_TIME_FOR_LOADING)
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
