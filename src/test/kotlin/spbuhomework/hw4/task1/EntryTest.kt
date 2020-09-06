package spbuhomework.hw4.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class EntryTest {

    @Test
    fun testToString_nonEmptyEntry_correctWork() {
        val myEntry = Entry("Hello", "world")
        assertEquals("(Hello : world)", myEntry.toString())
    }
}