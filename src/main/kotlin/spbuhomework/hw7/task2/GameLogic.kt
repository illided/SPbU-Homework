package spbuhomework.hw7.task2

import spbuhomework.hw7.task2.players.Bot
import spbuhomework.hw7.task2.players.Player
import spbuhomework.hw7.task2.views.WinScreen
import tornadofx.Controller
import tornadofx.FXEvent

class ButtonTextChange(val coordinate: Pair<Int, Int>, val newText: Char) : FXEvent()
class PlayerMadeMove(val playerChar: Char) : FXEvent()
object GameLogic : Controller() {
    var gameOver = false
    private lateinit var currentPlayer: Player
    private lateinit var playerWaiting: Player

    private fun update() {
        if (!currentPlayer.buttonPressReceived) {
            makeTurn()
            checkForGameOver()
            if (!gameOver) {
                currentPlayer.isMyTurn = false
                playerWaiting.isMyTurn = true
                currentPlayer = playerWaiting.also { playerWaiting = currentPlayer }
                runAsync {
                    if (currentPlayer is Bot) {
                        (currentPlayer as Bot).triggerPressing()
                    }
                }
            } else {
                find<WinScreen>().openWindow()
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
        currentPlayer.buttonPressReceived = true
        val keyPressed = currentPlayer.buttonPressed
        fire(ButtonTextChange(keyPressed, currentPlayer.playerChar))
        currentPlayer.myMoves.add(keyPressed)
        playerWaiting.opponentMoves.add(keyPressed)
    }

    fun refresh() {
        gameOver = false
        currentPlayer = GameModel.firstPlayer
        playerWaiting = GameModel.secondPlayer
        currentPlayer.isMyTurn = true
        currentPlayer.playerChar = 'X'
        playerWaiting.playerChar = 'O'
        for (x in 0 until SIDE_LENGTH) {
            for (y in 0 until SIDE_LENGTH) {
                fire(ButtonTextChange(Pair(x, y), ' '))
            }
        }
        subscribe<PlayerMadeMove> {
            if (it.playerChar == currentPlayer.playerChar) {
                update()
            }
        }
        if (currentPlayer is Bot) {
            (currentPlayer as Bot).triggerPressing()
        }
    }
}
