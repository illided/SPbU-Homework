import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task2KtTest {

    @Test
    fun recursiveFactorial_standardInput_mathematicallyCorrectOutput() {
        assertEquals(6, recursiveFactorial(3))
        assertEquals(120, recursiveFactorial(5))
    }

    @Test
    fun recursiveFactorial_zeroAsInput_oneAsOutput() {
        assertEquals(1, recursiveFactorial(0))
    }

    @Test
    fun recursiveFactorial_negativeInput_oneAsOutput() {
        assertEquals(1, recursiveFactorial(-3))
        assertEquals(1, recursiveFactorial(-120))
    }

    @Test
    fun cyclicFactorial_standardInput_mathematicallyCorrectOutput() {
        assertEquals(6, cyclicFactorial(3))
        assertEquals(120, cyclicFactorial(5))
    }

    @Test
    fun cyclicFactorial_zeroAsInput_oneAsOutput() {
        assertEquals(1, cyclicFactorial(0))
    }

    @Test
    fun cyclicFactorial_negativeInput_oneAsOutput() {
        assertEquals(1, cyclicFactorial(-3))
        assertEquals(1, cyclicFactorial(-120))
    }
}