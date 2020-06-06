package spbuhomework.hw7.task2

import tornadofx.Controller
import tornadofx.FXEvent

class ButtonTextChange(val coordinate: Pair<Int, Int>, val newText: Char) : FXEvent()
class GameLogic : Controller() {
    var gameOver = false
    var lastKeyPressed = Pair(0, 0)
    var currentPlayer = GameModel.firstPlayer
    var playerWaiting = GameModel.secondPlayer

    fun update() {
        currentPlayer.triggerPressing()
        if (!currentPlayer.buttonPressReceived) {
            makeTurn()
            checkForGameOver()
            currentPlayer.isMyTurn = false
            playerWaiting.isMyTurn = true
            currentPlayer = playerWaiting.also { playerWaiting = currentPlayer }
            if (!gameOver) {
                update()
            }
        }
    }

    private fun checkForGameOver() {
        for (i in 0 until SIDE_LENGTH) {
            if (currentPlayer.myMoves.filter { it.first == i }.size == SIDE_LENGTH ||
                currentPlayer.myMoves.filter { it.second == i }.size == SIDE_LENGTH
            ) {
                gameOver = true
            }
        }
        if (currentPlayer.myMoves.containsAll(listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))) ||
            currentPlayer.myMoves.containsAll(listOf(Pair(2, 0), Pair(1, 1), Pair(0, 2)))
        ) {
            gameOver = true
        }
        if (gameOver) {
            GameModel.winnerMessage.value = "${currentPlayer.playerChar} wins!"
        } else if (currentPlayer.myMoves.size + playerWaiting.myMoves.size == SIDE_LENGTH * SIDE_LENGTH) {
            gameOver = true
            GameModel.winnerMessage.value = "Draw!"
        }
    }

    private fun makeTurn() {
        val keyPressed = currentPlayer.buttonPressed
        fire(ButtonTextChange(keyPressed, currentPlayer.playerChar))
        currentPlayer.buttonPressReceived = true
        currentPlayer.myMoves.add(keyPressed)
        playerWaiting.opponentMoves.add(keyPressed)
        lastKeyPressed = keyPressed
    }

    init {
        GameModel.firstPlayer.isMyTurn = true
        GameModel.firstPlayer.playerChar = 'X'
        GameModel.secondPlayer.playerChar = 'O'
        for (x in 0 until SIDE_LENGTH) {
            for (y in 0 until SIDE_LENGTH) {
                fire(ButtonTextChange(Pair(x, y), ' '))
            }
        }
        update()
    }
}
