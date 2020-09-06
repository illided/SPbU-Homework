package spbuhomework.hw6

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StandardQuickSortTest {
    private val basicQuicksort = StandardQuickSort()

    @Test
    fun basicQuicksort_smallRandomArray_correctOutput() {
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), basicQuicksort.sortArray(arrayOf(4, 3, 5, 2, 1)))
    }

    @Test
    fun basicQuickSort_smallAlreadySortedArray_sameOutput() {
        val myArray = arrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(myArray, basicQuicksort.sortArray(myArray))
    }

    @Test
    fun basicQuickSort_biggerRandomArray_correctOutput() {
        val myArray = arrayOf(8, 7, 12, 16, 1, 11, 13, 5, 10, 4, 3, 2, 14, 6, 9, 17, 15, 20, 19, 18)
        assertArrayEquals(myArray.sorted().toTypedArray(), basicQuicksort.sortArray(myArray))
    }

    @Test
    fun basicQuickSort_biggerAlreadySortedArray_sameOutput() {
        val myArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        assertArrayEquals(myArray, basicQuicksort.sortArray(myArray))
    }

    @Test
    fun basicQuickSort_emptyArray_emptyArrayOutput() {
        assertArrayEquals(arrayOf<Int>(), basicQuicksort.sortArray(arrayOf<Int>()))
    }
}
