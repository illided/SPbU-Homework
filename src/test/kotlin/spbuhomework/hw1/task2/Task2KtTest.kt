package spbuhomework.hw1.task2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task2KtTest {

    @Test
    fun recursiveFactorial_standardInput_mathematicallyCorrectOutput() {
        assertEquals(6, recursiveFactorial(3))
    }

    @Test
    fun recursiveFactorial_bigInput_mathematicallyCorrectOutput() {
        assertEquals(1307674368000, recursiveFactorial(15))
    }

    @Test
    fun recursiveFactorial_zeroAsInput_oneAsOutput() {
        assertEquals(1, recursiveFactorial(0))
    }

    @Test
    fun recursiveFactorial_negativeInput_oneAsOutput() {
        assertEquals(1, recursiveFactorial(-3))
    }

    @Test
    fun cyclicFactorial_standardInput_mathematicallyCorrectOutput() {
        assertEquals(6, cyclicFactorial(3))
    }

    @Test
    fun cyclicFactorial_bigInput_mathematicallyCorrectOutput() {
        assertEquals(1307674368000, cyclicFactorial(15))
    }

    @Test
    fun cyclicFactorial_zeroAsInput_oneAsOutput() {
        assertEquals(1, cyclicFactorial(0))
    }

    @Test
    fun cyclicFactorial_negativeInput_oneAsOutput() {
        assertEquals(1, cyclicFactorial(-3))
    }
}