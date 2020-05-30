package spbuhomework.hwAVL

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NodeTest {

    @Test
    fun balance_noChildren_sameNodeAsOutput() {
        val myNode = Node(5, "Hello")
        assertEquals(myNode, myNode.balance())
    }

    @Test
    fun balance_twoChildrenNoBalanceNeeded_sameNodeAsOutput() {
        val myNode = Node(5, "Hello", Node(3, "Hey"), Node(7, "Java"))
        assertEquals(myNode, myNode.balance())
    }

    @Test
    fun balance_childOverloadAtRight_firstRightChildAsOutput() {
        val grandson = Node(13, "and java").balance()
        val son = Node(10, "Kotlin", rightChild = grandson).balance()
        val grandfather = Node(7, "Hello", rightChild = son)
        assertEquals(son, grandfather.balance())
    }

    @Test
    fun balance_childOverloadAtLeft_firstLeftChuldAsOutput() {
        val grandson = Node(13, "and java").balance()
        val son = Node(15, "Kotlin", leftChild = grandson).balance()
        val grandfather = Node(18, "Hello", leftChild = son)
        assertEquals(son, grandfather.balance())
    }

    @Test
    fun toEntry_nodeWithDataAsInput_entryAsOutput() {
        val myEntry = Node(5, "Hello").toEntry()
        assertTrue(myEntry.key == 5 && myEntry.value == "Hello")
    }
}