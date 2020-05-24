package spbuhomework.hw5.task1

class Node {
    internal class Edge(val letter: Char, var neighbour: Node)

    private val connectedEdges: MutableList<Edge> = mutableListOf()

    private var isTerminated = false

    private fun findEdge(char: Char): Edge? {
        return connectedEdges.find { it.letter == char }
    }

    fun appendNode(input: String) {
        if (input.isEmpty()) {
            isTerminated = true
        } else {
            var nextNodeEdge = findEdge(input[0])
            if (nextNodeEdge == null) {
                nextNodeEdge = Edge(input[0], Node())
                connectedEdges.add(nextNodeEdge)
            }
            nextNodeEdge.neighbour.appendNode(input.drop(1))
        }
    }

    fun isContains(input: String): Boolean {
        return if (input.isEmpty()) {
            isTerminated
        } else {
            findEdge(input[0])?.neighbour?.isContains(input.drop(1)) ?: false
        }
    }

    fun removeAndCheckIfUseless(input: String): Boolean {
        return when {
            input.isEmpty() -> {
                isTerminated = false
                connectedEdges.isEmpty()
            }
            findEdge(input[0])?.neighbour?.removeAndCheckIfUseless(input.drop(1)) == true -> {
                connectedEdges.remove(findEdge(input[0]))
                !isTerminated && connectedEdges.isEmpty()
            }
            else -> false
        }
    }

    fun getValuesForNode(): MutableList<String> {
        val result: MutableList<String> = mutableListOf()
        for (edge in connectedEdges) {
            val branchNodesValues = edge.neighbour.getValuesForNode()
            result.addAll(branchNodesValues.map { edge.letter + it })
        }
        if (isTerminated) {
            result.add("")
        }
        return result
    }

    fun getNumberOfPrefixes(input: String): Int {
        return if (input.isEmpty()) {
            connectedEdges.size
        } else {
            findEdge(input[0])?.neighbour?.getNumberOfPrefixes(input.drop(1)) ?: 0
        }
    }
}