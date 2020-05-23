package spbuhomework.hw4.task2

class Calculator(initialInputString: String) {

    companion object {
        private const val OPERATIONS = "+-/*"
        private const val REGEX = "((?<!^..)\\([*-\\/\\+0-9 ()]+\\)(?=\$))" +
                "|((?<=^..)\\([*-\\/\\+0-9 ()]+\\)(?= ))" +
                "|\\d+" +
                "|^[+-\\/*]"
        private const val MAX_ARGUMENT_NUM = 3
    }

    private lateinit var root: Node

    var result: Double = 0.0
    private set

    var inputString = ""
        set(value) {
            require("[+-/*() 0-9]+".toRegex().matches(value)) { "Invalid characters found" }
            root = generateTree(value)
            result = root.value
            field = value
        }

    init {
        inputString = initialInputString
    }

    private fun generateTree(input: String): Node {
        require(input.isNotEmpty()) { "Wrong command input" }
        val formattedInput = getListOfOperatorsInputs(input)
        return if (OPERATIONS.contains(formattedInput[0])) {
            Operator(formattedInput[0], generateTree(formattedInput[1]), generateTree(formattedInput[2]))
        } else {
            NumberOperand(formattedInput[0].toDouble())
        }
    }

    private fun getListOfOperatorsInputs(inputString: String): List<String> {
        val regex = REGEX.toRegex()
        val listOfOperators = regex.findAll(
            inputString.removePrefix("(").removeSuffix(")")
        ).toList().map { it.value }
        require(listOfOperators.size == MAX_ARGUMENT_NUM || listOfOperators.size == 1) { "Wrong number of arguments" }
        return listOfOperators
    }

    fun print() {
        val frontElements: MutableList<Node> = mutableListOf(root)
        val secondFrontElements: MutableList<Node> = mutableListOf()
        while (frontElements.isNotEmpty() || secondFrontElements.isNotEmpty()) {
            if (frontElements.isNotEmpty()) {
                secondFrontElements.addAll(frontElements[0].print())
                frontElements.removeAt(0)
            } else {
                println()
                frontElements.addAll(secondFrontElements)
                secondFrontElements.clear()
            }
        }
    }
}
