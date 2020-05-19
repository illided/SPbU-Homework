package spbuhomework.hw1.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IllegalArgumentException

internal class Task1KtTest {

    @Test
    fun splitAndRejoin_shortStandardInput_correctOutput() {
        assertArrayEquals(
            intArrayOf(1, 2),
            splitAndRejoin(1, 1, intArrayOf(2, 1))
        )
    }

    @Test
    fun splitAndRejoin_longerStandardInput_correctOutput() {
        assertArrayEquals(
            intArrayOf(3, 4, 5, 6, 1, 2),
            splitAndRejoin(2, 4, intArrayOf(1, 2, 3, 4, 5, 6))
        )
    }

    @Test
    fun splitAndRejoin_WrongBoundsInput_exceptionReceived() {
        assertThrows(IllegalArgumentException::class.java) {
            splitAndRejoin(1, 3, intArrayOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun splitAndRejoin_firstNegativeBoundInput_exceptionReceived() {
        assertThrows(IllegalArgumentException::class.java) {
            splitAndRejoin(-1, 6, intArrayOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun splitAndRejoin_secondNegativeBoundInput_exceptionReceived() {
        assertThrows(IllegalArgumentException::class.java) {
            splitAndRejoin(8, -3, intArrayOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun splitAndRejoin_bothNegativeBoundInput_exceptionReceived() {
        assertThrows(IllegalArgumentException::class.java) {
            splitAndRejoin(-2, -3, intArrayOf(1, 2, 3, 4, 5))
        }
    }

    @Test
    fun splitAndRejoin_OneBoundIsZeroInput_SameArrayOutput() {
        assertArrayEquals(
            intArrayOf(1),
            splitAndRejoin(0, 1, intArrayOf(1))
        )
    }
}
