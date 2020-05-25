package spbuhomework.hw5.task1

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

internal class TrieTest {

    @Test
    fun add_itemWasnotInTrie_trueAsOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        assertTrue(myTrie.add("Hi"))
    }

    @Test
    fun add_itemWasInTrie_falseAsOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        assertFalse(myTrie.add("Hello"))
    }

    @Test
    fun getSize_emptyTrie_zeroAsOutput() {
        val myTrie = Trie()
        assertEquals(0, myTrie.size)
    }

    @Test
    fun getSize_nonEmptyTrie_correctOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        assertEquals(7, myTrie.size)
    }

    @Test
    fun getSize_nonEmptyTrieItemWasDeleted_correctOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        myTrie.remove("student")
        assertEquals(6, myTrie.size)
    }

    @Test
    fun isContains_itemWasInTrie_trueAsOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        assertTrue(myTrie.isContains("student"))
    }

    @Test
    fun isContains_itemWasNotInTrie_falseAsOutput() {
        val myTrie = Trie()
        "Hello world i am a SPbU student".split(" ").forEach { myTrie.add(it) }
        assertFalse(myTrie.isContains("hse"))
    }

    @Test
    fun remove_itemWasInTree_trueAsOutput() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        assertTrue(myTrie.remove("she"))
    }

    @Test
    fun remove_itemWasNotInTree_falseAsOutput() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        assertFalse(myTrie.remove("java"))
    }

    private fun Array<String>.contentEquals(otherArray: Array<String>): Boolean {
        for (el in this) {
            if (!otherArray.contains(el)) {
                return false
            }
        }
        return true
    }

    @Test
    fun getValues_someDataInput_sameDataAsOutput() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        assertTrue(
            "she sheep kotlin he her herself hero".split(" ").toTypedArray().contentEquals(myTrie.values.toTypedArray())
        )
    }

    @Test
    fun getValues_emptyTrie_emptyListAsOutput() {
        val myTrie = Trie()
        assertArrayEquals(arrayOf<String>(), myTrie.values.toTypedArray())
    }

    @Test
    fun howManyStartWithPrefix_nonEmptyTrie_correctOutput() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        assertEquals(4, myTrie.howManyStartWithPrefix("he"))
    }

    @Test
    fun howManyStartWithPrefix_nonEmptyTrieItemWasDeleted_correctOutput() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        myTrie.remove("he")
        assertEquals(3, myTrie.howManyStartWithPrefix("he"))
    }

    @Test
    fun howManyStartWithPrefix_emptyTrie_correctOutput() {
        val myTrie = Trie()
        assertEquals(0, myTrie.howManyStartWithPrefix("his"))
    }

    private val testFilePath = "src/test/kotlin/spbuhomework/hw5/task1/"

    @Test
    fun deserialize_fromEmptyStream_getEmptyTrie() {
        val myTrie = Trie()
        val emptyFile = File(testFilePath + "emptyFile.txt")
        myTrie.deserialize(emptyFile.inputStream())
        assertArrayEquals(arrayOf<String>(), myTrie.values.toTypedArray())
    }

    @Test
    fun deserialize_fromNonEmptyCorrectStream_getNonEmptyTrie() {
        val myTrie = Trie()
        val emptyFile = File(testFilePath + "nonEmptyCorrectFile.txt")
        require(emptyFile.exists()) { "File does not exist or have different path" }
        myTrie.deserialize(emptyFile.inputStream())
        assertTrue(
            arrayOf(
                "Alice",
                "was",
                "beginning",
                "to",
                "get",
                "very",
                "tired",
                "of",
                "sitting",
                "by",
                "her",
                "sister",
                "on",
                "the",
                "bank"
            ).contentEquals(myTrie.values.toTypedArray())
        )
    }

    @Test
    fun deserialize_fromNonEmptyNonCorrectStream_exceptionThrown() {
        val myTrie = Trie()
        val emptyFile = File(testFilePath + "nonEmptyNonCorrectFile.txt")
        require(emptyFile.exists()) { "File does not exist or have different path" }
        assertThrows(IOException::class.java) {
            myTrie.deserialize(emptyFile.inputStream())
        }
    }

    @Test
    fun serialize_nonEmptyTrie_correctWork() {
        val myTrie = Trie()
        "she sheep kotlin he her herself hero".split(" ").forEach { myTrie.add(it) }
        val output = FileOutputStream(testFilePath + "nonEmptyTrie.txt")
        myTrie.serialize(output)
        output.close()

        val expected = File(testFilePath + "nonEmptyTrieExpectations.txt")
        val actual = File(testFilePath + "nonEmptyTrie.txt")
        assertEquals(expected.readText(), actual.readText())
    }

    @Test
    fun serialize_emptyTrie_correctWork() {
        val myTrie = Trie()
        val output = FileOutputStream(testFilePath + "emptyTrie.txt")
        myTrie.serialize(output)
        output.close()

        val expected = File(testFilePath + "emptyFile.txt")
        val actual = File(testFilePath + "emptyTrie.txt")
        assertEquals(expected.readText(), actual.readText())
    }
}