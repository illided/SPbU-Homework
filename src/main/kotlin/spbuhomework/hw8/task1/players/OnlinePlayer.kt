package spbuhomework.hw8.task1.players

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.features.websocket.ws
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.readText
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import spbuhomework.hw8.task1.GameModel
import spbuhomework.hw8.task1.PlayerMadeMove
import tornadofx.Controller
import java.util.concurrent.atomic.AtomicBoolean

class OnlinePlayer : Player, Controller() {
    override var playerChar: Char = ' '

    override var isMyTurn: Boolean = false

    override var buttonPressReceived: Boolean = true

    override var buttonPressed: Pair<Int, Int> = Pair(0, 0)

    override val myMoves: MutableList<Pair<Int, Int>> = mutableListOf()

    override val opponentMoves: MutableList<Pair<Int, Int>> = mutableListOf()

    var needToBeSend = AtomicBoolean(false)
    var opponentKeyPressed = Pair(0, 0)

    fun sendMessage(keyPressed: Pair<Int, Int>) {
        opponentKeyPressed = keyPressed
        needToBeSend.set(true)
        println("Want to send message")
    }

    @KtorExperimentalAPI
    fun setConnection() {
        OnlineConnector().start()
    }

    inner class OnlineConnector : Thread() {
        private var opponentChar = ' '

        private suspend fun receiveMove(socket: WebSocketSession) {
            if (buttonPressReceived && isMyTurn) {
                val frame = socket.incoming.receive()
                if (frame is Frame.Text) {
                    val receivedMessage = frame.readText()
                    println("received message: $receivedMessage")
                    if (receivedMessage[0] == 'B') {
                        buttonPressed =
                            Pair(receivedMessage[1].toInt() - '0'.toInt(), receivedMessage[2].toInt() - '0'.toInt())
                        buttonPressReceived = false
                        fire(PlayerMadeMove(playerChar))
                        println("Made move ${buttonPressed.first}${buttonPressed.second}")
                    }
                }
            }
        }

        private suspend fun sendMove(socket: WebSocketSession) {
            if (needToBeSend.get()) {
                socket.send(
                    Frame.Text("$opponentChar${opponentKeyPressed.first}${opponentKeyPressed.second}")
                )
                println("Sending $opponentChar${opponentKeyPressed.first}${opponentKeyPressed.second}")
                needToBeSend.set(false)
            }
        }

        @KtorExperimentalAPI
        override fun run() {
            runBlocking {
                val client = HttpClient { install(WebSockets) }
                client.ws(method = io.ktor.http.HttpMethod.Get, port = 8080, host = "127.0.0.1", path = "/") {
                    when (val frame = incoming.receive()) {
                        is Frame.Text -> {
                            val input = frame.readText()
                            if (input[0] == 'C') {
                                playerChar = input[1]
                                opponentChar = if (playerChar == 'X') 'O' else 'X'
                                println("Char $playerChar received for online opponent, human player: $opponentChar")
                            }
                        }
                    }
                    while (!GameModel.gameOver) {
                        receiveMove(this)
                        sendMove(this)
                    }
                }
            }
        }
    }
}
