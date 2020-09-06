package spbuhomework.hw4.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OperatorTest {

    @Test
    fun evaluate_multiplicationAsOperation_correctOutput() {
        val operator = Operator("*", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(80.0, operator.evaluate())
    }

    @Test
    fun evaluate_additionAsOperation_correctOutput() {
        val operator = Operator("+", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(18.0, operator.evaluate())
    }

    @Test
    fun evaluate_differenceAsOperation_correctOutput() {
        val operator = Operator("-", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(2.0, operator.evaluate())
    }

    @Test
    fun evaluate_divisionAsOperation_correctOutput() {
        val operator = Operator("/", NumberOperand(10.0), NumberOperand(5.0))
        assertEquals(2.0, operator.evaluate())
    }

    @Test
    fun testToString_someOperation_correctOperationStringPresentation() {
        val operator = Operator("-", NumberOperand(16.0), NumberOperand(16.0))
        assertEquals("-", operator.toString())
    }

    @Test
    fun getChildrenList_NumberOperatorAsChildren_correctOutput() {
        val leftChild = NumberOperand(78.0)
        val rightChild = NumberOperand(89.0)
        assertArrayEquals(
            arrayOf(leftChild, rightChild),
            Operator("-", leftChild, rightChild).childrenList.toTypedArray()
        )
    }

    @Test
    fun getChildrenList_OperatorsAsChildren_correctOutput() {
        val leftChild = Operator("-", NumberOperand(16.0), NumberOperand(16.0))
        val rightChild = Operator("-", NumberOperand(10.0), NumberOperand(8.0))
        assertArrayEquals(
            arrayOf(leftChild, rightChild),
            Operator("-", leftChild, rightChild).childrenList.toTypedArray()
        )
    }
}