package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.players.HumanPlayer
import tornadofx.*

class SideChoosing : View("My View") {
    override val root = stackpane {
        label("Choose side") {
            setPrefSize(400.0, 100.0)
        }.alignment = Pos.TOP_CENTER
        hbox {
            button("X") {
                setPrefSize(200.0, 100.0)
                action {
                    GameModel.firstPlayer = HumanPlayer()
                    GameModel.secondPlayer = GameModel.opponent
                    this@SideChoosing.replaceWith<GameField>(sizeToScene = true)
                }
            }
            button("O") {
                setPrefSize(200.0, 100.0)
                action {
                    GameModel.firstPlayer = GameModel.opponent
                    GameModel.secondPlayer = HumanPlayer()
                    this@SideChoosing.replaceWith<GameField>(sizeToScene = true)
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }
}
