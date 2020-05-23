package spbuhomework.hw4.task2

import java.io.File

private const val DEFAULT_FILE_PATH = "./src/main/kotlin/spbuhomework/hw4/task2/"

fun main() {
    val myFile = File(DEFAULT_FILE_PATH + "text.txt")
    if (!myFile.exists()) {
        println("File does not exist")
    } else {
        val tree = Calculator(myFile.readLines()[0])
        println("Tree:")
        tree.print()
        println("\nResult = ${tree.result}")
    }
}
