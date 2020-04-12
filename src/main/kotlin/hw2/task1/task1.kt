package spbuhomework.hw2.task1

const val THRESHOLD = 3

fun getNumberOfExcessSymbols(inputString: String): Int {
    var currentNumOfX = 0
    var answer = 0
    for (char in inputString) {
        if (char == 'x') {
            currentNumOfX++
        } else {
            if (currentNumOfX > THRESHOLD) {
                answer += currentNumOfX - THRESHOLD
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
