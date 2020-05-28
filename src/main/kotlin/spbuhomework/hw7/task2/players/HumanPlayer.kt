package spbuhomework.hw7.task2.players

import spbuhomework.hw7.task2.views.ButtonPushed
import tornadofx.*

class HumanPlayer : Player, Controller() {

    override var myMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override var opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override fun triggerPressing(lastKeyPressed: Pair<Int, Int>) {}
    override var playerChar: Char = ' '

    override var isMyTurn: Boolean = false

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)

    override var buttonPressReceived: Boolean = true

    init {
        subscribe<ButtonPushed> { event ->
            if (buttonPressReceived && isMyTurn && !myMoves.contains(event.coordinate) && !opponentMoves.contains(event.coordinate)) {
                buttonPressed = event.coordinate
                buttonPressReceived = false
                print("Button press")
            }
        }
    }
}

