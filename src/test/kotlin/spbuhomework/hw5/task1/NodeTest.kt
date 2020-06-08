package spbuhomework.hw5.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NodeTest {
    @Test
    fun isContains_itemInSubTrie_trueAsOutput() {
        val node = Node()
        node.appendNode("Hello")
        assertTrue(node.isContains("Hello"))
    }

    @Test
    fun isContains_itemNotInSubTrie_falseAsOutput() {
        val node = Node()
        node.appendNode("Hello")
        assertFalse(node.isContains("Kotlin"))
    }

    @Test
    fun removeAndCheckIfUseless_nodeIsUseless_trueAsOutput() {
        val node = Node()
        node.appendNode("Hello")
        assertTrue(node.removeAndCheckIfUseless("Hello"))
    }

    @Test
    fun removeAndCheckIfUseless_nodeIsUseful_falseAsOutput() {
        val node = Node()
        "Hero Hello".split(" ").forEach { node.appendNode(it) }
        assertFalse(node.removeAndCheckIfUseless("Hello"))
    }

    @Test
    fun removeAndCheckIfUseless_nodeWasUsefulButNowUseless_trueAsOutput() {
        val node = Node()
        "Hero Hello".split(" ").forEach { node.appendNode(it) }
        node.removeAndCheckIfUseless("Hero")
        assertTrue(node.removeAndCheckIfUseless("Hello"))
    }

    @Test
    fun getValuesForNode_nonEmptySubTrie_correctOutput() {
        val node = Node()
        "Hero Hello".split(" ").forEach { node.appendNode(it) }
        assertArrayEquals(arrayOf("Hero", "Hello"), node.getValuesForNode().toTypedArray())
    }

    @Test
    fun getValuesForNode_emptySubTrie_correctOutput() {
        val node = Node()
        assertArrayEquals(arrayOf(), node.getValuesForNode().toTypedArray())
    }

    @Test
    fun getNumberOfPrefixes_nonEmptyPrefix_correctOutput() {
        val node = Node()
        "Hero Hello He Sheep She Egor Zachti Pozhaluista".split(" ").forEach { node.appendNode(it) }
        assertEquals(3, node.getNumberOfPrefixes("He"))
    }

    @Test
    fun getNumberOfPrefixes_emptyPrefix_numberOfWordsInSubTrie() {
        val node = Node()
        "Hero Hello He Sheep She Egor Zachti Pozhaluista".split(" ").forEach { node.appendNode(it) }
        assertEquals(8, node.getNumberOfPrefixes(""))
    }
}