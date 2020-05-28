package spbuhomework.hw7.task2.players

import spbuhomework.hw7.task2.SIDE_LENGTH


class EasyBot : Player {
    override var playerChar: Char = ' '

    override var isMyTurn: Boolean = false

    override fun triggerPressing(lastKeyPressed: Pair<Int, Int>) {
        buttonPressed = List(9) {
            Pair(
                it % SIDE_LENGTH,
                it / SIDE_LENGTH
            )
        }.filter { !myMoves.contains(it) && !opponentMoves.contains(it) }.random()
        buttonPressReceived = false
    }

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)
    override var myMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override val opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override var buttonPressReceived: Boolean = true

}