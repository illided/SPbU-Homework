package spbuhomework.hw8.task1.views

import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.BUTTON_HEIGHT
import spbuhomework.hw8.task1.ButtonTextChange
import spbuhomework.hw8.task1.GAME_FIELD_BUTTON_WIDTH
import spbuhomework.hw8.task1.SIDE_LENGTH
import spbuhomework.hw8.task1.GameLogic
import tornadofx.View
import tornadofx.vbox
import tornadofx.hbox
import tornadofx.button
import tornadofx.action
import tornadofx.FXEvent

class GameField : View("TicTacToe") {
    override val root = vbox {
        for (y in 0 until SIDE_LENGTH) {
            hbox {
                for (x in 0 until SIDE_LENGTH) {
                    button {
                        setPrefSize(GAME_FIELD_BUTTON_WIDTH, BUTTON_HEIGHT)
                        action {
                            if (!GameModel.gameOver) {
                                fire(ButtonPushed(Pair(x, y)))
                            }
                        }
                        subscribe<ButtonTextChange> {
                            if (it.coordinate == Pair(x, y)) {
                                text = it.newText.toString()
                            }
                        }
                    }
                }
            }
        }
    }
    private lateinit var logic: GameLogic
    override fun onDock() {
        super.onDock()
        logic = GameLogic()
    }
}

class ButtonPushed(val coordinate: Pair<Int, Int>) : FXEvent()
