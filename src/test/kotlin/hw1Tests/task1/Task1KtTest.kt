package hw1.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import splitAndRejoin

internal class Task1KtTest {

    @Test
    fun splitAndRejoin_StandartInput_correctOutput() {
        assertArrayEquals(
            intArrayOf(3, 4, 5, 6, 1, 2).toTypedArray(),
            splitAndRejoin(2, 4, intArrayOf(1, 2, 3, 4, 5, 6).toTypedArray())
        )
        assertArrayEquals(
            intArrayOf(1, 2).toTypedArray(),
            splitAndRejoin(1, 1, intArrayOf(2, 1).toTypedArray())
        )
    }

    @Test
    fun splitAndRejoin_WrongBoundsInput_NullOutput(){
        assertArrayEquals(
            null,
            splitAndRejoin(5, 3, intArrayOf(1, 2, 3, 4, 5, 6, 7).toTypedArray())
        )
        assertArrayEquals(
            null,
            splitAndRejoin(2, 2, intArrayOf(1, 2, 3, 4, 5, 6).toTypedArray())
        )
    }

    @Test
    fun splitAndRejoin_NegativeBoundsInput_NullOutput(){
        assertArrayEquals(
            null,
            splitAndRejoin(-4, 3, intArrayOf(1, 2, 3, 4, 5, 6, 7).toTypedArray())
        )
        assertArrayEquals(
            null,
            splitAndRejoin(-2, 3, intArrayOf(1, 2, 3, 4, 5, 6).toTypedArray())
        )
    }

    @Test
    fun splitAndRejoin_OneBoundIsZeroInput_SameArrayOutput(){
        assertArrayEquals(
            intArrayOf(1).toTypedArray(),
            splitAndRejoin(0, 1, intArrayOf(1).toTypedArray())
        )
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4).toTypedArray(),
            splitAndRejoin(0, 4, intArrayOf(1, 2, 3, 4).toTypedArray())
        )
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4).toTypedArray(),
            splitAndRejoin(4, 0, intArrayOf(1, 2, 3, 4).toTypedArray())
        )
    }
}