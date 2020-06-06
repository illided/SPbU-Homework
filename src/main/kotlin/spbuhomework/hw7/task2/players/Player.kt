package spbuhomework.hw7.task2.players

interface Player {
    var playerChar: Char
    var isMyTurn: Boolean
    fun triggerPressing()
    var buttonPressReceived: Boolean
    var buttonPressed: Pair<Int, Int>
    var myMoves: MutableList<Pair<Int, Int>>
    val opponentMoves: MutableList<Pair<Int, Int>>
}