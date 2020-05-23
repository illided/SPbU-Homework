package spbuhomework.hw4.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class NumberOperandTest {

    @Test
    fun getOperandString_doubleAsValue_correctOutput() {
        val numberOperand = NumberOperand(80.0)
        assertEquals("80.0", numberOperand.operandString)
    }

    @Test
    fun getValue_doubleAsValue_correctOutput() {
        val numberOperand = NumberOperand(80.0)
        assertEquals(80.0, numberOperand.value)
    }
}