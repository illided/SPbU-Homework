package spbuhomework.hw1.task3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Task3KtTest {

    @Test
    fun getNumberOfInclusions_mainStringContainSubString_nonZeroOutput() {
        assertEquals(2, getNumberOfInclusions("Hello Iceland and Greenland", "land"))
    }

    @Test
    fun getNumberOfInclusions_mainStringDontContainSubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Zachtite dz pozhaluista", "net"))
    }

    @Test
    fun getNumberOfInclusions_mainStringShorterThanSubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Life", "University"))
    }

    @Test
    fun getNumberOfInclusions_emptyMainString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("", "Orange"))
    }

    @Test
    fun getNumberOfInclusions_emptySubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Hello", ""))
    }

    @Test
    fun getNumberOfInclusiond_mainStringAndSubStringAreBothEmpty_oneAsOutput() {
        assertEquals(1, getNumberOfInclusions("", ""))
    }
}