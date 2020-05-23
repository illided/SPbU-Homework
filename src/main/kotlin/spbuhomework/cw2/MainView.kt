package spbuhomework.cw2

import tornadofx.*
import java.lang.Thread.sleep

class MainView : View() {
    var sideLength = 4
    private val logic = GameLogic(sideLength)
    fun setToSpace(id: String) {
    }


    override val root = vbox {
        for (x in 1..sideLength) {
            hbox {
                for (y in 1..sideLength) {
                    button {
                        setPrefSize(50.0, 50.0)
                        id = logic.getIndexInNumList(x, y).toString()
                        action {
                            logic.buttonClicked(x, y)
                            if (logic.previousButtonId == 0){
                                logic.previousButtonId = this.id.toInt()
                            }
                            if (logic.isActive(x, y)) {
                                this.text = this.id.toString()
                                sleep(300)
                                this.text = " "
                                setToSpace(logic.previousButtonId.toString())
                            } else {
                                text = logic.getIndexInNumList(x, y).toString()
                            }
                        }
                    }
                }
            }
        }
    }
}