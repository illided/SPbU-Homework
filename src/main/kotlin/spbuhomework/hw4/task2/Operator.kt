package spbuhomework.hw4.task2

class Operator(
    override val operandString: String,
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

    val operation = Operation.values().find { it.index == operandString }?.function
        ?: throw IllegalArgumentException("Wrong command input")

    override val value: Double
        get() = operation(leftOperand.value, rightOperand.value)

    override fun print(): List<Node> {
        print("$operandString ")
        return listOf(leftOperand, rightOperand)
    }
}