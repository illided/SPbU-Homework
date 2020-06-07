package spbuhomework.hw8.task1

import spbuhomework.hw8.task1.players.Bot
import spbuhomework.hw8.task1.players.OnlinePlayer
import spbuhomework.hw8.task1.views.GameOverScreen
import tornadofx.Controller
import tornadofx.FXEvent

class ButtonTextChange(val coordinate: Pair<Int, Int>, val newText: Char) : FXEvent()
class PlayerMadeMove(val playerChar: Char) : FXEvent()
class GameLogic : Controller() {
    var currentPlayer = GameModel.firstPlayer
    var playerWaiting = GameModel.secondPlayer

    private fun update() {
        if (!currentPlayer.buttonPressReceived) {
            makeTurn()
            if (playerWaiting is OnlinePlayer) {
                (playerWaiting as OnlinePlayer).sendMessage(currentPlayer.buttonPressed)
            }
            checkForGameOver()
            currentPlayer.isMyTurn = false
            playerWaiting.isMyTurn = true
            currentPlayer = playerWaiting.also { playerWaiting = currentPlayer }
            if (!GameModel.gameOver) {
                if (currentPlayer is Bot) {
                    (currentPlayer as Bot).triggerPressing()
                }
            } else {
                find<GameOverScreen>().openWindow()
            }
        }
    }

    private fun checkForGameOver() {
        for (i in 0 until SIDE_LENGTH) {
            if (currentPlayer.myMoves.filter { it.first == i }.size == SIDE_LENGTH ||
                currentPlayer.myMoves.filter { it.second == i }.size == SIDE_LENGTH
            ) {
                GameModel.gameOver = true
            }
        }
        if (currentPlayer.myMoves.containsAll(listOf(Pair(0, 0), Pair(1, 1), Pair(2, 2))) ||
            currentPlayer.myMoves.containsAll(listOf(Pair(2, 0), Pair(1, 1), Pair(0, 2)))
        ) {
            GameModel.gameOver = true
        }
        if (GameModel.gameOver) {
            GameModel.winnerMessage.value = "${currentPlayer.playerChar} wins!"
        } else if (currentPlayer.myMoves.size + playerWaiting.myMoves.size == SIDE_LENGTH * SIDE_LENGTH) {
            GameModel.gameOver = true
            GameModel.winnerMessage.value = "Draw!"
        }
    }

    private fun makeTurn() {
        val keyPressed = currentPlayer.buttonPressed
        fire(ButtonTextChange(keyPressed, currentPlayer.playerChar))
        println("${currentPlayer.playerChar} moved at $keyPressed")
        currentPlayer.buttonPressReceived = true
        currentPlayer.myMoves.add(keyPressed)
        playerWaiting.opponentMoves.add(keyPressed)
    }

    init {
        println("----NEW_GAME")
        GameModel.firstPlayer.isMyTurn = true
        GameModel.firstPlayer.playerChar = 'X'
        GameModel.secondPlayer.playerChar = 'O'
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
