package spbuhomework.hw4.task2

class Operator(
    private val operandString: String,
    private val leftOperand: Node,
    private val rightOperand: Node
) : Node {

    companion object {
        enum class Operation(val index: String, val function: (Double, Double) -> Double) {
            Addition("+", { a: Double, b: Double -> a + b }),
            Difference("-", { a: Double, b: Double -> a - b }),
            Multiplication("*", { a: Double, b: Double -> a * b }),
            Division("/", { a: Double, b: Double -> a / b })
        }
    }

    private val operation = Operation.values().find { it.index == operandString }?.function
        ?: throw IllegalArgumentException("Wrong command input")

    override fun evaluate(): Double = operation(leftOperand.evaluate(), rightOperand.evaluate())

    override fun toString(): String {
        return operandString
    }

    override val childrenList: List<Node> = listOf(leftOperand, rightOperand)
}
