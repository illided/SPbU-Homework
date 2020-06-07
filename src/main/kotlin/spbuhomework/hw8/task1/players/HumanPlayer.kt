package spbuhomework.hw8.task1.players

import spbuhomework.hw8.task1.PlayerMadeMove
import spbuhomework.hw8.task1.views.ButtonPushed
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
                fire(PlayerMadeMove(playerChar))
            }
        }
        println("Human player created")
    }
}
