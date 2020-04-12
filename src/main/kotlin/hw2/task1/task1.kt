package spbuhomework.hw2.task1

fun getNumberOfExcessSymbols(inputString: String): Int {
    var currentNumOfX = 0
    val treshold = 3
    var answer = 0
    for (char in inputString) {
        if (char == 'x') {
            currentNumOfX++
        } else {
            if (currentNumOfX > treshold) {
                answer += currentNumOfX - treshold
            }
            currentNumOfX = 0
        }
    }
    return answer
}

fun main() {
    val answer = readLine()?.let { getNumberOfExcessSymbols(it) }
    println("Answer: $answer")
}
