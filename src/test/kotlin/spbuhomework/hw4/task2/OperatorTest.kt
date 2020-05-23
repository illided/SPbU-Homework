package spbuhomework.hw4.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class OperatorTest {

    @Test
    fun getValue_multiplicationAsOperation_correctOutput() {
        val operator = Operator("*", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(80.0, operator.value)
    }

    @Test
    fun getValue_additionAsOperation_correctOutput() {
        val operator = Operator("+", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(18.0, operator.value)
    }

    @Test
    fun getValue_differenceAsOperation_correctOutput() {
        val operator = Operator("-", NumberOperand(10.0), NumberOperand(8.0))
        assertEquals(2.0, operator.value)
    }

    @Test
    fun getValue_divisionAsOperation_correctOutput() {
        val operator = Operator("/", NumberOperand(10.0), NumberOperand(5.0))
        assertEquals(2.0, operator.value)
    }
}