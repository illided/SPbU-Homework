package spbuhomework.hw7.task2.views

import spbuhomework.hw7.task2.*
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
                            if (!logic.gameOver) {
                                fire(ButtonPushed(Pair(x, y)))
                                logic.update()
                                if (logic.gameOver) {
                                    find<WinScreen>().openWindow()
                                }
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
