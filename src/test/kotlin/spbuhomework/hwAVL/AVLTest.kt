package spbuhomework.hwAVL

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class AVLTest {

    @Test
    fun getSize() {
    }

    @Test
    fun containsKey() {
    }

    @Test
    fun containsValue() {
    }

    @Test
    fun get() {
    }

    @Test
    fun isEmpty() {
    }

    @Test
    fun getEntries() {
    }

    @Test
    fun getKeys() {
    }

    @Test
    fun getValues() {
    }

    @Test
    fun clear() {
    }

    @Test
    fun put() {
        val myAVL = AVL<Int, Int>()
        myAVL[5] = 5
        myAVL[1] = 4
        myAVL[3] = 7
        assertEquals(myAVL[5], 5)
    }

    @Test
    fun putAll() {
    }

    @Test
    fun remove() {
    }
}