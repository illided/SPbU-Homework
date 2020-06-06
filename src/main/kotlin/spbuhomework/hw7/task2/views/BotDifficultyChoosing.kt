package spbuhomework.hw7.task2.views

import javafx.geometry.Pos
import spbuhomework.hw7.task2.BUTTON_HEIGHT
import spbuhomework.hw7.task2.MENU_BUTTON_WIDTH
import spbuhomework.hw7.task2.GameModel
import spbuhomework.hw7.task2.players.EasyBot
import spbuhomework.hw7.task2.players.HardBot
import tornadofx.View
import tornadofx.stackpane
import tornadofx.label
import tornadofx.vbox
import tornadofx.button
import tornadofx.action

class BotDifficultyChoosing : View("TicTacToe") {
    override val root = stackpane {
        label("Choose difficulty") {
            setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
        }.alignment = Pos.TOP_CENTER
        vbox {
            button("Easy") {
                setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
                action {
                    GameModel.opponent = EasyBot()
                    toNextView()
                }
            }
            button("Hard") {
                setPrefSize(MENU_BUTTON_WIDTH, BUTTON_HEIGHT)
                action {
                    GameModel.opponent = HardBot()
                    toNextView()
                }
            }
        }.alignment = Pos.BOTTOM_CENTER
    }

    private fun toNextView() {
        this.replaceWith<SideChoosing>()
    }
}
