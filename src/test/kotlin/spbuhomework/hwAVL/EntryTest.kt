package spbuhomework.hwAVL

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EntryTest {

    @Test
    fun testToString_nonEmptyEntry_nonEmptyStringAsOutput() {
        val myEntry = Entry(5, "Hello kotlin")
        assertEquals(myEntry.toString(), "(5 : Hello kotlin)")
    }

    @Test
    fun testToString_nonEmptyEntryInTheEntry_nonEmptyStringAsOutput() {
        val myEntry = Entry(Entry("I", "love"), Entry("math and", "programming"))
        assertEquals(myEntry.toString(), "((I : love) : (math and : programming))")
    }
}