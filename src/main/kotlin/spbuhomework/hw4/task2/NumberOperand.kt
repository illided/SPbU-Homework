package spbuhomework.hw4.task2

class NumberOperand(override val value: Double) : Node {
    override val operandString: String
        get() = value.toString()

    override fun print(): List<Node> {
        print("$operandString ")
        return listOf()
    }
}
