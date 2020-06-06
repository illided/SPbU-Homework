package spbuhomework.hw7.task2.players

import spbuhomework.hw7.task2.SIDE_LENGTH

class HardBot : Player {
    override var buttonPressReceived: Boolean = true
    override var playerChar: Char = ' '
    override var isMyTurn: Boolean = false

    override fun triggerPressing() {
        val potentialButtonPressed = getBestMove()
        if (potentialButtonPressed != null) {
            buttonPressed = potentialButtonPressed
            buttonPressReceived = false
        }
    }

    companion object {
        private const val MAX_VALUE = 1000
        private const val MIN_VALUE = -MAX_VALUE
        private const val BASIC_VALUE = 10
    }

    private fun getBestMove(): Pair<Int, Int>? {
        var bestMove: Pair<Int, Int>? = null
        var bestScore = MIN_VALUE
        for (move in getAvailableMoves()) {
            myMoves.add(move)
            val newScore = minimax(myMoves, opponentMoves, false)
            if (newScore > bestScore) {
                bestMove = move
                bestScore = newScore
            }
            myMoves.remove(move)
        }
        return bestMove
    }

    private fun minimax(
        myMoves: MutableList<Pair<Int, Int>>,
        opponentMoves: MutableList<Pair<Int, Int>>,
        isMaximizer: Boolean
    ): Int {
        val isMyWin = checkForWinOrDraw(myMoves, opponentMoves)
        val isOpponentWin = checkForWinOrDraw(opponentMoves, myMoves)
        return when {
            isMyWin && !isOpponentWin -> BASIC_VALUE
            !isMyWin && isOpponentWin -> -BASIC_VALUE
            isMyWin && isOpponentWin -> 0
            isMaximizer -> playerHandling(myMoves, opponentMoves, isMaximizer)
            else -> opponentHandling(myMoves, opponentMoves, isMaximizer)
        }
    }

    private fun playerHandling(
        myMoves: MutableList<Pair<Int, Int>>,
        opponentMoves: MutableList<Pair<Int, Int>>,
        isMaximizer: Boolean
    ): Int {
        var best = MIN_VALUE
        for (move in getAvailableMoves()) {
            myMoves.add(move)
            best = best.coerceAtLeast(minimax(myMoves, opponentMoves, !isMaximizer))
            myMoves.remove(move)
        }
        return best
    }

    private fun opponentHandling(
        myMoves: MutableList<Pair<Int, Int>>,
        opponentMoves: MutableList<Pair<Int, Int>>,
        isMaximizer: Boolean
    ): Int {
        var best = MAX_VALUE
        for (move in getAvailableMoves()) {
            opponentMoves.add(move)
            best = best.coerceAtMost(minimax(myMoves, opponentMoves, !isMaximizer))
            opponentMoves.remove(move)
        }
        return best
    }


    private fun getAvailableMoves(): List<Pair<Int, Int>> {
        return List(9) {
            Pair(
                it % SIDE_LENGTH,
                it / SIDE_LENGTH
            )
        }.filter { !myMoves.contains(it) && !opponentMoves.contains(it) }
    }

    private fun checkForWinOrDraw(playerMoves: List<Pair<Int, Int>>, opponentMoves: List<Pair<Int, Int>>): Boolean {
        var answer = false
        if (playerMoves.size + opponentMoves.size == SIDE_LENGTH * SIDE_LENGTH) {
            answer = true
        }
        for (i in 0 until SIDE_LENGTH) {
            if (playerMoves.filter { it.first == i }.size == SIDE_LENGTH ||
                playerMoves.filter { it.second == i }.size == SIDE_LENGTH
            ) {
                answer = true
            }
        }
        if (playerMoves.containsAll(listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))) ||
            playerMoves.containsAll(listOf(Pair(2, 0), Pair(1, 1), Pair(0, 2)))
        ) {
            answer = true
        }
        return answer
    }

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)
    override var myMoves: MutableList<Pair<Int, Int>> = mutableListOf()
    override val opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()
}
