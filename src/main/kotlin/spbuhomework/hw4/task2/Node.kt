package spbuhomework.hw4.task2

interface Node {
    fun evaluate(): Double
    val childrenList: List<Node>
}
