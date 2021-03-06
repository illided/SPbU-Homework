package spbuhomework.hw5.task1

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.io.Serializable

class Trie : Serializable {
    private var root = Node()

    val size
        get() = root.getNumberOfPrefixes("")

    fun isContains(input: String): Boolean = root.isContains(input)

    fun add(input: String): Boolean {
        if (root.isContains(input)) {
            return false
        } else {
            root.appendNode(input)
            return true
        }
    }

    fun remove(input: String): Boolean {
        if (!root.isContains(input)) {
            return false
        } else {
            root.removeAndCheckIfUseless(input)
            return true
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

    fun writeObject(output: OutputStream) {
        val currentValues = values
        output.write(currentValues.joinToString(" ").toByteArray())
    }

    fun readObject(input: InputStream) {
        root = Node()
        val text = input.bufferedReader().use { it.readText() }.split(" ", "\n").filter { it.isNotEmpty() }
        for (word in text) {
            if (word.isWord()) {
                this.add(word)
            } else {
                throw IOException("Wrong characters in input stream")
            }
        }
    }
}
