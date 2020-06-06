package spbuhomework.hw5.task1

class Node {
    private val connectedEdges: MutableMap<Char, Node> = mutableMapOf()

    private var wordsWithPrefixNum = 0

    private var isTerminated = false

    fun appendNode(input: String) {
        wordsWithPrefixNum++
        if (input.isEmpty()) {
            isTerminated = true
        } else {
            if (!connectedEdges.contains(input[0])) {
                connectedEdges[input[0]] = Node()
            }
            connectedEdges[input[0]]?.appendNode(input.drop(1))
        }
    }

    fun isContains(input: String): Boolean {
        if (input.isEmpty()) {
            return isTerminated
        }
        return connectedEdges[input[0]]?.isContains(input.drop(1)) ?: false
    }

    fun removeAndCheckIfUseless(input: String): Boolean {
        wordsWithPrefixNum--
        return when {
            input.isEmpty() -> {
                isTerminated = false
                connectedEdges.isEmpty()
            }
            connectedEdges[input[0]]?.removeAndCheckIfUseless(input.drop(1)) == true -> {
                connectedEdges.remove(input[0])
                !isTerminated && connectedEdges.isEmpty()
            }
            else -> false
        }
    }

    fun getValuesForNode(): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        for (edge in connectedEdges) {
            val branchNodesValues = edge.value.getValuesForNode()
            result.addAll(branchNodesValues.map { edge.key + it })
        }
        if (isTerminated) {
            result.add("")
        }
        return result
    }

    fun getNumberOfPrefixes(input: String): Int {
        if (input.isNotEmpty()) {
            return connectedEdges[input[0]]?.getNumberOfPrefixes(input.drop(1)) ?: 0
        }
        return wordsWithPrefixNum
    }
}
