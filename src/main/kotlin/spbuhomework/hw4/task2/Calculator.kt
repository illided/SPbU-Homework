package spbuhomework.hw4.task2

class Calculator(expression: String) {

    companion object {
        private val REGEX_FOR_EVALUATING_ARGUMENTS =
            ("(?<!^..)(\\([*-\\/\\\\+0-9 ()]+\\))(?=\$)" +
                    "|((?<=^..)(\\([*-\\/\\\\+0-9 ()]+\\))(?= (\\d\$|\\([+-\\/*])))" +
                    "|\\d+" +
                    "|^[+-\\/*]").toRegex()
        private const val ONE_WORDED_ARGUMENT = 1
        private const val THREE_WORDED_ARGUMENT = 3
        private val POSSIBLE_ARGUMENT_NUM_FOR_OPERATIONS = arrayOf(ONE_WORDED_ARGUMENT, THREE_WORDED_ARGUMENT)
    }

    private var root: Node

    val result: Double
        get() = root.evaluate()

    init {
        root = generateTree(expression)
    }

    private fun generateTree(input: String): Node {
        require(input.isNotEmpty()) { "Wrong command input" }

        val formattedInput = REGEX_FOR_EVALUATING_ARGUMENTS.findAll(
            input.removePrefix("(").removeSuffix(")")
        ).toList().map { it.value }
        require(POSSIBLE_ARGUMENT_NUM_FOR_OPERATIONS.contains(formattedInput.size)) { "Wrong number of arguments" }

        return if (formattedInput.size == THREE_WORDED_ARGUMENT) {
            val (operatorIndex, leftExpression, rightExpression) = formattedInput
            Operator(operatorIndex, generateTree(leftExpression), generateTree(rightExpression))
        } else {
            val numberValue = formattedInput[0]
            NumberOperand(numberValue.toDouble())
        }
    }

    override fun toString(): String {
        val frontElements: MutableList<Node> = mutableListOf(root)
        val secondFrontElements: MutableList<Node> = mutableListOf()
        val stringList: MutableList<String> = mutableListOf()
        while (frontElements.isNotEmpty() || secondFrontElements.isNotEmpty()) {
            if (frontElements.isNotEmpty()) {
                stringList.add(frontElements[0].toString())
                secondFrontElements.addAll(frontElements[0].childrenList)
                frontElements.removeAt(0)
            } else {
                stringList.add("\n")
                frontElements.addAll(secondFrontElements)
                secondFrontElements.clear()
            }
        }
        return stringList.joinToString(" ")
    }
}
