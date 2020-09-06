package spbuhomework.cw2

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BubbleSortKtTest {

    @Test
    fun bubbleSort_emptyArrayAsInput_emptyArrayAsOutput() {
        assertArrayEquals(arrayOf(), bubbleSort(arrayOf(), naturalOrder<Int>()))
    }

    @Test
    fun bubbleSort_nonEmptyNonSortedIntegerArray_sortedArrayAsOutput() {
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 7, 8, 9),
            bubbleSort(arrayOf(5, 1, 4, 3, 2, 9, 8, 7), naturalOrder())
        )
    }

    @Test
    fun bubbleSort_nonEmptySortedIntegerArray_sameArrayAsOutput() {
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 7, 8, 9),
            bubbleSort(arrayOf(1, 2, 3, 4, 5, 7, 8, 9), naturalOrder())
        )
    }

    @Test
    fun bubbleSort_nonEmptyNonSortedStringArray_sortedArrayAsOutput() {
        assertArrayEquals(
            arrayOf("aaa", "aba", "cde", "ced", "fff"),
            bubbleSort(arrayOf("fff", "aba", "aaa", "cde", "ced"), naturalOrder())
        )
    }

    @Test
    fun bubbleSort_nonEmptySortedStringArray_sameArrayAsOutput() {
        assertArrayEquals(
            arrayOf("aaa", "aba", "cde", "ced", "fff"),
            bubbleSort(arrayOf("aaa", "aba", "cde", "ced", "fff"), naturalOrder())
        )
    }

    @Test
    fun bubbleSort_nonEmptyNonSortedPairArrayWithUserComparator_sortedArrayAsOutput() {
        assertArrayEquals(
            arrayOf(Pair(1, 1), Pair(2, 2), Pair(3, 1)),
            bubbleSort(
                arrayOf(Pair(2, 2), Pair(3, 1), Pair(1, 1)),
                CoordinateDistanceFromStartComparator()
            )
        )
    }

    @Test
    fun bubbleSort_nonEmptySortedPairArrayWithUserComparator_sameArrayAsOutput() {
        assertArrayEquals(
            arrayOf(Pair(1, 1), Pair(2, 2), Pair(3, 1)),
            bubbleSort(
                arrayOf(Pair(1, 1), Pair(2, 2), Pair(3, 1)),
                CoordinateDistanceFromStartComparator()
            )
        )
    }
}
