package spbuhomework.hw7.task2

import tornadofx.Controller
import tornadofx.FXEvent

class ButtonTextChange(val coordinate: Pair<Int, Int>, val newText: Char) : FXEvent()

class GameLogic : Controller() {

    var gameOver = false
    var lastKeyPressed = Pair(0, 0)
    private val buttonPressed = mutableListOf<Pair<Int, Int>>()
    var currentPlayer = GameModel.firstPlayer
    var playerWaiting = GameModel.secondPlayer

    fun update() {
        currentPlayer.triggerPressing(lastKeyPressed)
        if (!currentPlayer.buttonPressReceived) {
            makeTurn()
            currentPlayer.isMyTurn = false
            playerWaiting.isMyTurn = true
            currentPlayer = playerWaiting.also { playerWaiting = currentPlayer }
            checkForGameOver()
            update()
        }
    }

    private fun checkForGameOver() {
        if (currentPlayer.myMoves.size + playerWaiting.myMoves.size == 9) {
            gameOver = true
            GameModel.winnerMessage = "Draw!"
        }
    }

    private fun makeTurn() {
        val keyPressed = currentPlayer.buttonPressed
        fire(ButtonTextChange(keyPressed, currentPlayer.playerChar))
        println("${keyPressed.first} ${keyPressed.second} must change to ${currentPlayer.playerChar}")
        currentPlayer.buttonPressReceived = true
        currentPlayer.myMoves.add(keyPressed)
        playerWaiting.opponentMoves.add(keyPressed)
        buttonPressed.add(keyPressed)
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
