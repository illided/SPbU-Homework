package spbuhomework.hw8.task1

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.websocket.WebSockets
import io.ktor.websocket.webSocket
import java.util.Collections
import kotlin.collections.LinkedHashSet

object CommunicationWord {
    const val PLAYER_CHAR = "C"
    const val BUTTON_PRESS = "B"
}

fun main() {
    val server = embeddedServer(Netty, port = 8080) {
        mainModule()
    }
    server.start(wait = true)
}

fun Application.mainModule() {
    install(WebSockets)
    var currentPlayerChar = 'X'
    val players: MutableList<Player> = mutableListOf()
    routing {
        webSocket("/") {
            val newPlayer = Player(this, ' ')
            when {
                players.isEmpty() -> {
                    newPlayer.playerChar = 'O'
                }
                players.size == 1 -> {
                    newPlayer.playerChar = 'X'
                }
            }
            players += newPlayer
            send(Frame.Text(CommunicationWord.PLAYER_CHAR + newPlayer.playerChar))
            try {
                while (true) {
                    var playerMove = ""
                    when (val frame = incoming.receive()) {
                        is Frame.Text -> {
                            playerMove = frame.readText()
                        }
                    }
                    if (playerMove[0] == currentPlayerChar) {
                        for (player in players) {
                            if (player != newPlayer) {
                                player.session.outgoing.send(
                                    Frame.Text(CommunicationWord.BUTTON_PRESS + playerMove.drop(1))
                                )
                            }
                        }
                    }
                    currentPlayerChar = if (currentPlayerChar == 'X') 'O' else 'X'
                }
            } finally {
                players -= newPlayer
                currentPlayerChar = 'X'
            }
        }
    }
}

data class Player(val session: WebSocketSession, var playerChar: Char)
