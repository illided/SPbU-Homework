package spbuhomework.hwAVL

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.lang.IndexOutOfBoundsException

internal class AVLTest {

    @Test
    fun put_someDataAdded_correctWork() {
        /* Just checking if it doesn't crash
        * put will be tested with others functions*/
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
    }

    @Test
    fun getSize_someDataAdded_rightSizeAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertEquals(10, myAVL.size)
    }

    @Test
    fun getSize_someDataAddedAndSomeDeleted_rightSizeAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        myAVL.remove(7)
        myAVL.remove(8)
        assertEquals(8, myAVL.size)
    }

    @Test
    fun getSize_emptyAvl_zeroAsOutput() {
        val myAvl = AVL<Int, Int>()
        assertEquals(0, myAvl.size)
    }

    @Test
    fun containsKey_itemExist_trueAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertEquals(true, myAVL.containsKey(7))
    }

    @Test
    fun containsKey_itemDoesntExist_falseAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertEquals(false, myAVL.containsKey(11))
    }

    @Test
    fun containsValue_itemExist_trueAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertTrue(myAVL.containsValue(7))
    }

    @Test
    fun containsValue_itemDoesntExist_falseAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertFalse(myAVL.containsValue(1))
    }

    @Test
    fun get_itemExist_itsValueAsOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertEquals(8, myAVL[3])
    }

    @Test
    fun get_itemDoesntExist_exceptionThrown() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            myAVL[11]
        }
    }

    @Test
    fun isEmpty_emptyAVL_trueAsOutput() {
        val myAVL = AVL<Int, Int>()
        assertTrue(myAVL.isEmpty())
    }

    @Test
    fun remove_itemExist_itemIsNotInAVLAnymore() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        myAVL.remove(7)
        assertFalse(myAVL.containsKey(7))
    }

    @Test
    fun remove_itemDoesNotExist_exceptionThrown() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..10) {
            myAVL.put(i, i + 5)
        }
        assertThrows(IndexOutOfBoundsException::class.java) {
            myAVL.remove(11)
        }
    }

    @Test
    fun testToString_someDataInput_correctOutput() {
        val myAVL = AVL<Int, Int>()
        for (i in 1..5) {
            myAVL.put(i, i + 4)
        }
        assertEquals("{(1 : 5)(2 : 6)(3 : 7)(4 : 8)(5 : 9)}", myAVL.toString())
    }
}