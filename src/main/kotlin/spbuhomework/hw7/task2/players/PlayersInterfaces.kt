package spbuhomework.hw7.task2.players

interface Player {
    var playerChar: Char
    var isMyTurn: Boolean
    var buttonPressReceived: Boolean
    var buttonPressed: Pair<Int, Int>
    var myMoves: MutableList<Pair<Int, Int>>
    val opponentMoves: MutableList<Pair<Int, Int>>
}

interface Bot : Player {
    fun triggerPressing()
}
