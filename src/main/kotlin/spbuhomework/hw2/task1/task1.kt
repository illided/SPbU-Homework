package spbuhomework.hw2.task1

fun getNumberOfExcessSymbols(inputString: String, threshold: Int = 2): Int {
    var currentNumOfX = 0
    var answer = 0
    for (char in inputString) {
        if (char == 'x') {
            currentNumOfX++
        } else {
            if (currentNumOfX > threshold) {
                answer += currentNumOfX - threshold
            }
            currentNumOfX = 0
        }
    }
    answer += if (currentNumOfX > threshold) currentNumOfX - threshold else 0
    return answer
}

fun main() {
    val inputString = readLine()
    if (inputString == null) {
        println("Wrong input")
    } else {
        print("Answer: ${getNumberOfExcessSymbols(inputString)}")
    }
}
