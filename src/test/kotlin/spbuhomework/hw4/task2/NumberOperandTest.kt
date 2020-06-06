package spbuhomework.hw4.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NumberOperandTest {

    @Test
    fun evaluate_someValue_sameValueOutput() {
        val myNumberOperand = NumberOperand(56.0)
        assertEquals(56.0, myNumberOperand.evaluate())
    }

    @Test
    fun testToString_someValue_sameValueButStringAsOutput() {
        val myNumberOperand = NumberOperand(56.0)
        assertEquals("56.0", myNumberOperand.toString())
    }

    @Test
    fun getChildrenList_someValue_emptyListAsOutput() {
        val myNumberOperand = NumberOperand(37.0)
        assertEquals(listOf<Node>(), myNumberOperand.childrenList)
    }
}