package spbuhomework.hw8.task1

import javafx.beans.property.SimpleStringProperty
import spbuhomework.hw8.task1.players.Player

object GameModel {
    lateinit var opponent: Player

    lateinit var firstPlayer: Player
    lateinit var secondPlayer: Player

    var gameOver = false
    var winnerMessage = SimpleStringProperty("")
}
