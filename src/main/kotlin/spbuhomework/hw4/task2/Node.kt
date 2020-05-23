package spbuhomework.hw4.task2

interface Node {
    val value: Double
    val operandString: String
    fun print(): List<Node>
}