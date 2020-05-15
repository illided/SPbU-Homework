package spbuhomework.hw6

import kotlinx.coroutines.experimental.runBlocking
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Real_quick_sortKtTest {

    @Test
    fun basicQuicksort_smallRandomList_correctOutput() {
        assertEquals(listOf(1, 2, 3, 4, 5), basicQuicksort(listOf(4, 3, 5, 2, 1)))
    }

    @Test
    fun basicQuickSort_smallAlreadySortedList_sameOtput() {
        val myList = listOf(1, 2, 3, 4, 5)
        assertEquals(myList, basicQuicksort(myList))
    }

    @Test
    fun basicQuickSort_biggerRandomList_correctOutput() {
        val myList = mutableListOf(8, 7, 12, 16, 1, 11, 13, 5, 10, 4, 3, 2, 14, 6, 9, 17, 15, 20, 19, 18)
        assertEquals(myList.sorted(), basicQuicksort(myList))
    }

    @Test
    fun basicQuickSort_biggerAlreadySortedList_sameOutput() {
        val myList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        assertEquals(myList, basicQuicksort(myList))
    }

    @Test
    fun basicQuickSort_emptyList_emptyListOutput() {
        assertEquals(listOf<Int>(), basicQuicksort(listOf<Int>()))
    }

    @Test
    fun realQuicksort_smallRandomList_correctOutput() {
        runBlocking { assertEquals(listOf(1, 2, 3, 4, 5),
            realQuicksort(listOf(4, 3, 5, 2, 1))
        ) }
    }

    @Test
    fun realQuickSort_smallAlreadySortedList_sameOtput() {
        val myList = listOf(1, 2, 3, 4, 5)
        runBlocking { assertEquals(myList, realQuicksort(myList)) }
    }

    @Test
    fun realQuickSort_biggerRandomList_correctOutput() {
        val myList = mutableListOf(8, 7, 12, 16, 1, 11, 13, 5, 10, 4, 3, 2, 14, 6, 9, 17, 15, 20, 19, 18)
        runBlocking { assertEquals(myList.sorted(), realQuicksort(myList)) }
    }

    @Test
    fun realQuickSort_biggerAlreadySortedList_sameOutput() {
        val myList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)
        runBlocking { assertEquals(myList, realQuicksort(myList)) }
    }

    @Test
    fun realQuickSort_emptyList_emptyListOutput() {
        runBlocking { assertEquals(listOf<Int>(), realQuicksort(listOf<Int>())) }
    }
}