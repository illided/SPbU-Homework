package spbuhomework.hw7.task2.views

import spbuhomework.hw7.task2.ButtonTextChange
import spbuhomework.hw7.task2.GameLogic
import spbuhomework.hw7.task2.SIDE_LENGTH
import tornadofx.*

class GameField : View("TicTacToe") {
    override val root = vbox {
        for (y in 0 until SIDE_LENGTH) {
            hbox {
                for (x in 0 until SIDE_LENGTH) {
                    button {
                        setPrefSize(100.0, 100.0)
                        action {
                            fire(ButtonPushed(Pair(x, y)))
                            logic.update()
                            if (logic.gameOver) {
                                find<WinScreen>().openWindow()
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

class ButtonPushed(val coordinate: Pair<Int, Int>): FXEvent()