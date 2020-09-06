package spbuhomework.hw4.task2

private const val DEFAULT_FILE_PATH = "spbuhomework/hw4/task2/"

fun main() {
    val expression = {}.javaClass.classLoader.getResource(DEFAULT_FILE_PATH + "text.txt")?.readText()
    if (expression == null) {
        println("File does not exist")
        return
    }
    val tree = Calculator(expression)
    println("Tree:\n $tree")
    println("\nResult = ${tree.result}")
}
