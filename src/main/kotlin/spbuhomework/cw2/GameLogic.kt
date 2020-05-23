package spbuhomework.cw2

class GameLogic(var sideLength: Int) {
    private var deadButtons: MutableList<Array<Int>> = mutableListOf<Array<Int>>()
    private var clickedButtons: MutableList<Array<Int>> = mutableListOf<Array<Int>>()
    var numList: MutableList<Int> = mutableListOf()
        private set

    init {
        require(sideLength >= 1) { "Side length can not be less than 1" }
        val numOfNumbers = (sideLength * sideLength) / 2
        for (i in 1..numOfNumbers) {
            numList.addAll(listOf(i, i))
        }
        numList.shuffle()
    }

    fun getIndexInNumList(x: Int, y: Int): Int {
        return numList[(x - 1) * sideLength + y - 1]
    }

    fun isActive(x: Int, y: Int): Boolean {
        return clickedButtons.find { it[0] == x && it[1] == y } == null &&
                deadButtons.find { it[0] == x && it[1] == y } == null
    }

    var previousButtonId = 0

    fun isRightCombination(): Boolean {
        return getIndexInNumList(
            clickedButtons[0][0],
            clickedButtons[0][1]
        ) == getIndexInNumList(
            clickedButtons[1][0],
            clickedButtons[1][1]
        )
    }

    fun buttonClicked(x: Int, y: Int) {
        if (isActive(x, y)) {
            clickedButtons.add(arrayOf(x, y))
            println("Button $x $y was added")
        }
        if (clickedButtons.size == 2) {
            if (isRightCombination()) {
                previousButtonId = getIndexInNumList(clickedButtons[0][0], clickedButtons[0][1])
                deadButtons.addAll(clickedButtons)
            }
            clickedButtons.clear()
        }
    }
}