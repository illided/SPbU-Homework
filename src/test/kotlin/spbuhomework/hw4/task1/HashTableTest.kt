package spbuhomework.hw4.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class HashTableTest {
    @Test
    fun put() {
        /* just checking if it doesn't crash
        * put will be tested with other functions*/
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), i.toString())
        }
    }

    @Test
    fun getSize_nonEmptyHashTable_correctOutput() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), i.toString())
        }
        assertEquals(100, myHashTable.size)
    }

    @Test
    fun getSize_emptyHashTable_zeroAsOutput() {
        val myHashTable = HashTable<String, String>()
        assertEquals(0, myHashTable.size)
    }

    @Test
    fun get_emptyHashTable_exceptionThrown() {
        val myHashTable = HashTable<String, String>()
        assertThrows(IndexOutOfBoundsException::class.java) {
            myHashTable.get("two number 9s, a number 9 large, a number 6 with extra dip...")
        }
    }

    @Test
    fun get_nonEmptyHashTableButItemDoesNotExist_exceptionThrown() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..10) {
            myHashTable.put(i.toString(), i.toString())
        }
        myHashTable.put("Kotlin", "is cool")
        assertThrows(IndexOutOfBoundsException::class.java) {
            myHashTable.get("Java")
        }
    }

    @Test
    fun get_nonEmptyHashTableAndItemExist_correctOutput() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..10) {
            myHashTable.put(i.toString(), i.toString())
        }
        myHashTable.put("Kotlin", "is cool")
        assertEquals("is cool", myHashTable.get("Kotlin"))
    }

    @Test
    fun isContains_itemDoesNotExist_falseAsOutput() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), (i + 5).toString())
        }
        assertFalse(myHashTable.isContains("135"))
    }

    @Test
    fun isContains_itemExist_trueAsOutput() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), (i + 5).toString())
        }
        assertTrue(myHashTable.isContains("78"))
    }

    @Test
    fun remove_itemExist_itemRemoved() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), (i + 5).toString())
        }
        myHashTable.remove("79")
        assertFalse(myHashTable.isContains("79"))
    }

    @Test
    fun remove_itemDoesNotExist_exceptionThrown() {
        val myHashTable = HashTable<String, String>()
        assertThrows(IndexOutOfBoundsException::class.java) {
            myHashTable.remove("Hello!")
        }
    }

    @Test
    fun setHashFunction_someDataAddedAndHashFunctionChanged_HashTableSuccessfullyRefiled() {
        val myHashTable = HashTable<String, String>()
        for (i in 1..100) {
            myHashTable.put(i.toString(), (i + 5).toString())
        }
        myHashTable.currentHasher = SimpleHasher()
        assertEquals(100, myHashTable.size)
    }
}
