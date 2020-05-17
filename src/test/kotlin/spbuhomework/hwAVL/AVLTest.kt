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
    fun put_emptyAVLDataInput_sameValueOutput() {
        val myAVL = AVL<Int, Int>()
        assertEquals(myAVL.put(7, 5), 5)
    }

    @Test
    fun put_nonEmptyAVLDataInput_sameValueOutput() {
        val myAVL = AVL<Int, Int>()
        myAVL.put(5, 5)
        myAVL.put(10, 9)
        myAVL.put(9, 8)
        assertEquals(myAVL.put(7, 5), 5)
    }

    @Test
    fun put_alreadyExistingElementInput_nullAsOutput() {
        val myAVL = AVL<Int, Int>()
        myAVL.put(8, 5)
        assertEquals(myAVL.put(8, 7), null)
    }

    @Test
    fun putAll() {
    }

    @Test
    fun remove() {
    }

    @Test
    fun put() {
    }

    @Test
    fun testToString() {
    }
}