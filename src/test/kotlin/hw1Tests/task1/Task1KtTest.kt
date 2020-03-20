import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task1KtTest {

    @Test
    fun splitAndRejoin_StandartInput_correctOutput() {
        assertArrayEquals(
            intArrayOf(3, 4, 5, 6, 1, 2),
            splitAndRejoin(2, 4, intArrayOf(1, 2, 3, 4, 5, 6))
        )
        assertArrayEquals(
            intArrayOf(1, 2),
            splitAndRejoin(1, 1, intArrayOf(2, 1))
        )
    }

    @Test
    fun splitAndRejoin_WrongBoundsInput_NullOutput() {
        assertArrayEquals(
            null,
            splitAndRejoin(5, 3, intArrayOf(1, 2, 3, 4, 5, 6, 7))
        )
        assertArrayEquals(
            null,
            splitAndRejoin(2, 2, intArrayOf(1, 2, 3, 4, 5, 6))
        )
    }

    @Test
    fun splitAndRejoin_NegativeBoundsInput_NullOutput() {
        assertArrayEquals(
            null,
            splitAndRejoin(-4, 3, intArrayOf(1, 2, 3, 4, 5, 6, 7))
        )
        assertArrayEquals(
            null,
            splitAndRejoin(-2, 3, intArrayOf(1, 2, 3, 4, 5, 6))
        )
    }

    @Test
    fun splitAndRejoin_OneBoundIsZeroInput_SameArrayOutput() {
        assertArrayEquals(
            intArrayOf(1),
            splitAndRejoin(0, 1, intArrayOf(1))
        )
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4),
            splitAndRejoin(0, 4, intArrayOf(1, 2, 3, 4))
        )
        assertArrayEquals(
            intArrayOf(1, 2, 3, 4),
            splitAndRejoin(4, 0, intArrayOf(1, 2, 3, 4))
        )
    }
}
