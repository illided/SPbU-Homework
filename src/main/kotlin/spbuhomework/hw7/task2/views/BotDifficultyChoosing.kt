package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.players.EasyBot
import tornadofx.*

class BotDifficultyChoosing : View("TicTacToe") {
    override val root = stackpane {
        label("Choose difficulty") {
            setPrefSize(400.0, 100.0)
        }.alignment = Pos.TOP_CENTER
        vbox {
            button("Easy") {
                setPrefSize(400.0, 100.0)
                action {
                    GameModel.opponent = EasyBot()
                    toNextView()
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }

    private fun toNextView() {
        this.replaceWith<SideChoosing>()
    }
}
