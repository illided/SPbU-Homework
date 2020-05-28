package spbuhomework.hw7.task2.views

import spbuhomework.hw7.task2.GameModel
import tornadofx.*

class WinScreen : View("My View") {
    override val root = stackpane {
        label(GameModel.winnerMessage)
        button("Back to main menu") {
            this@WinScreen.replaceWith<MainMenu>(sizeToScene = true)
        }
    }
}
