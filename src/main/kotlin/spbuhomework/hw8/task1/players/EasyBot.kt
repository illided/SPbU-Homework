package spbuhomework.hw8.task1.players

import spbuhomework.hw8.task1.PlayerMadeMove
import spbuhomework.hw8.task1.SIDE_LENGTH
import tornadofx.Controller

class EasyBot : Bot, Controller() {
    override var playerChar: Char = ' '

    override var isMyTurn: Boolean = false

    override fun triggerPressing() {
        val availableButtons = List(SIDE_LENGTH * SIDE_LENGTH) {
            Pair(
                it % SIDE_LENGTH,
                it / SIDE_LENGTH
            )
        }.filter { !myMoves.contains(it) && !opponentMoves.contains(it) }
        if (availableButtons.isNotEmpty()) {
            buttonPressed = availableButtons.random()
            buttonPressReceived = false
            fire(PlayerMadeMove(playerChar))
        }
    }

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)
    override var myMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override val opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override var buttonPressReceived: Boolean = true
}
