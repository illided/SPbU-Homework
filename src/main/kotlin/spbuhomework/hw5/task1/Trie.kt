package spbuhomework.hw5.task1

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class Trie : Serializable {
    private var root = Node()

    var size = 0
        private set

    fun isContains(input: String): Boolean = root.isContains(input)

    fun add(input: String): Boolean {
        return if (root.isContains(input)) {
            false
        } else {
            root.appendNode(input)
            size++
            true
        }
    }

    fun remove(input: String): Boolean {
        return if (!root.isContains(input)) {
            false
        } else {
            root.removeAndCheckIfUseless(input)
            size--
            true
        }
    }

    val values: List<String>
        get() {
            return root.getValuesForNode().toList()
        }

    fun howManyStartWithPrefix(input: String): Int {
        return root.getNumberOfPrefixes(input)
    }

    private fun String.isWord(): Boolean {
        return this.find { !it.isLetterOrDigit() } == null
    }

    override fun serialize(output: OutputStream) {
        val currentValues = values
        output.write(currentValues.joinToString(" ").toByteArray())
        output.close()
    }

    override fun deserialize(input: InputStream) {
        root = Node()
        val text = input.bufferedReader().use { it.readText() }.split(" ", "\n")
        for (word in text) {
            if (word.isWord()) {
                this.add(word)
            } else {
                throw IOException("Wrong characters in input stream")
            }
        }
    }

}
