package spbuhomework.hw2.task2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Task2KtTest {

    @Test
    fun processArray_arrayOfIntWithoutRepetitionsInput_sameOutput() {
        assertArrayEquals(arrayOf(1, 2, 3, 4, 5), leaveTheFirstRightInclusions(arrayOf(1, 2, 3, 4, 5)))
    }

    @Test
    fun processArray_arrayOfIntWithRepetitionsInput_correctOutput() {
        assertArrayEquals(arrayOf(1, 2, 3), leaveTheFirstRightInclusions(arrayOf(3, 1, 2, 1, 2, 3)))
    }

    @Test
    fun processArray_arrayOfStringsWithRepetitionsInput_sameOutput() {
        assertArrayEquals(
            arrayOf("it's", "me", "Hello", "world"),
            leaveTheFirstRightInclusions(arrayOf("Hello", "world", "it's", "me", "Hello", "world"))
        )
    }

    @Test
    fun processArray_arrayOfStringsWithoutRepetitionsInput_correctOutput() {
        assertArrayEquals(
            arrayOf("Ded", "lubit", "slivu"),
            leaveTheFirstRightInclusions(arrayOf("Ded", "lubit", "slivu"))
        )
    }

    @Test
    fun processArray_emptyArrayAsInput_emptyArrayAsOutput() {
        assertArrayEquals(Array(0) { "" }, leaveTheFirstRightInclusions(Array(0) { "" }))
    }
}