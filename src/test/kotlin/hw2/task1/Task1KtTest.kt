package spbuhomework.hw2.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task1KtTest {

    @Test
    fun getNumberOfExcessSymbols_shortStringWithExcessX_nonZeroOutput() {
        assertEquals(1, getNumberOfExcessSymbols("xxxxex"))
    }

    @Test
    fun getNumberOfExcessSymbols_longStringWithExcessX_nonZeroOutput() {
        assertEquals(3, getNumberOfExcessSymbols("xexexefxxxxevevnexxxxxaxaxa"))
    }

    @Test
    fun getNumberOfExcessSymbols_shortStringWithoutExcessX_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols("xxxexexe"))
    }

    @Test
    fun getNumberOfExcessSymbols_longStringWithoutExcessX_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols("xxxeexexexexexexexeaaxaxaxaxax"))
    }

    @Test
    fun getNumberOfExcessSymbols_emptyString_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols(""))
    }
}