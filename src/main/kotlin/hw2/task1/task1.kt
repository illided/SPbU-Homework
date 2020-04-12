package spbuhomework.hw2.task1

fun getNumberOfExcessSymbols(inputString: String) : Int {
    var treshold = 0
    var answer = 0
    for (char in inputString) {
        if (char == 'x') {
            treshold++
        } else {
            if (treshold > 3) {
                answer += treshold - 3
            }
            treshold = 0
        }
    }
    return answer
}

fun main() {
    val answer = readLine()?.let { getNumberOfExcessSymbols(it) }
    println("Answer: $answer")
}