package spbuhomework.hw7.task2.players

import spbuhomework.hw7.task2.views.ButtonPushed
import tornadofx.Controller

class HumanPlayer : Player, Controller() {

    override var myMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override var opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()

    override var playerChar: Char = ' '

    override var isMyTurn: Boolean = false

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)

    override var buttonPressReceived: Boolean = true

    init {
        subscribe<ButtonPushed> { event ->
            val isAvailable = !myMoves.contains(event.coordinate) && !opponentMoves.contains(event.coordinate)
            if (buttonPressReceived && isMyTurn && isAvailable) {
                buttonPressed = event.coordinate
                buttonPressReceived = false
            }
        }
    }
}
