package spbuhomework.hw2.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Task1KtTest {

    @Test
    fun getNumberOfExcessSymbols_shortStringWithExcessX_nonZeroOutput() {
        assertEquals(2, getNumberOfExcessSymbols("xxxxex"))
    }

    @Test
    fun getNumberOfExcessSymbols_longStringWithExcessX_nonZeroOutput() {
        assertEquals(5, getNumberOfExcessSymbols("xexexefxxxxevevnexxxxxaxaxa"))
    }

    @Test
    fun getNumberOfExcessSymbols_shortStringWithoutExcessX_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols("xxexexe"))
    }

    @Test
    fun getNumberOfExcessSymbols_longStringWithoutExcessX_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols("xxeexexexexexexexeaaxaxaxaxax"))
    }

    @Test
    fun getNumberOfExcessSymbols_emptyString_zeroOutput() {
        assertEquals(0, getNumberOfExcessSymbols(""))
    }

    @Test
    fun getNumberOfExcessSymbols_onlyX_correctOutput() {
        assertEquals(3, getNumberOfExcessSymbols("xxxxx"))
    }
}