package spbuhomework.hw4.task2

class NumberOperand(private val value: Double) : Node {
    override fun evaluate() = value
    override fun toString(): String {
        return value.toString()
    }

    override val childrenList: List<Node> = listOf()
}
