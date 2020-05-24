import java.io.File
import java.io.FileNotFoundException

fun countNonBlanks(inputFilePath: String): Int {
    var answer = 0
    if (!File(inputFilePath).exists()) {
        throw FileNotFoundException("File does not exist or have different path")
    }
    File(inputFilePath).forEachLine {
        if (!it.isBlank()) {
            answer++
        }
    }
    return answer
}

fun main() {
    try {
        println("Answer: ${countNonBlanks("src/main/kotlin/hw1/task5/mainTest.txt")}")
    } catch (exception: FileNotFoundException) {
        println(exception.message)
    }
}
