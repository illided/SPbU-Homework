package spbuhomework.hw4.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CalculatorTest {

    @Test
    fun getResult_oneNumberInput_sameNumberAsOutput() {
        val calculator = Calculator("8")
        assertEquals(8.0, calculator.result)
    }

    @Test
    fun getResult_smallInputString_correctOutput() {
        val calculator = Calculator("(+ 10 15)")
        assertEquals(25.0, calculator.result)
    }

    @Test
    fun getResult_biggerInputString_correctOutput() {
        val calculator = Calculator("(- (/ (* (+ 5 3) (+ 1 1)) (+ 2 2)) 3)")
        assertEquals(1.0, calculator.result)
    }
}