package spbuhomework.hw7.task2

import javafx.beans.property.SimpleStringProperty
import spbuhomework.hw7.task2.players.Player

object GameModel {
    lateinit var opponent: Player

    lateinit var firstPlayer: Player
    lateinit var secondPlayer: Player

    var winnerMessage = SimpleStringProperty("")
}