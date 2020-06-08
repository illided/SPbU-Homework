package spbuhomework.hw6

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AsyncQuickSortTest {

    private val asyncQuickSort = AsyncQuickSort()

    @Test
    fun asyncQuickSort_smallRandomArray_correctOutput() {
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), asyncQuickSort.sortArray(arrayOf(4, 3, 5, 2, 1)))
    }

    @Test
    fun asyncQuickSort_smallAlreadySortedArray_sameOutput() {
        val myArray = arrayOf(1, 2, 3, 4, 5)
        assertArrayEquals(myArray, asyncQuickSort.sortArray(myArray))
    }

    @Test
    fun asyncQuickSort_biggerRandomArray_correctOutput() {
        val myArray = arrayOf(8, 7, 12, 16, 1, 11, 13, 5, 10, 4, 3, 2, 14, 6, 9, 17, 15, 20, 19, 18)
        assertArrayEquals(myArray.sorted().toTypedArray(), asyncQuickSort.sortArray(myArray))
    }

    @Test
    fun asyncQuickSort_biggerAlreadySortedArray_sameOutput() {
        val myArray = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        assertArrayEquals(myArray, asyncQuickSort.sortArray(myArray))
    }

    @Test
    fun asyncQuickSort_emptyArray_emptyArrayOutput() {
        assertArrayEquals(arrayOf<Int>(), asyncQuickSort.sortArray(arrayOf<Int>()))
    }
}
