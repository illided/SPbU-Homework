package spbuhomework.hw1.task4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Task4KtTest {

    @Test
    fun isPalindrome_palindromeStringInput_trueAsOutput() {
        assertEquals(true, isPalindrome("racecar"))
    }

    @Test
    fun isPalindrome_longerPalindromeStringInput_trueAsOutput() {
        assertEquals(true, isPalindrome("Abbbba ded abbbbA"))
    }

    @Test
    fun isPalindrome_nonPalindromeStringInput_falseAsOutput() {
        assertEquals(false, isPalindrome("Kotlin"))
    }

    @Test
    fun isPalindrome_longerNonPalindromeStringInput_falseAsOutput() {
        assertEquals(false, isPalindrome("This string is not a palindrome"))
    }

    @Test
    fun isPalindrome_oneCharacterInput_trueAsOutput() {
        assertEquals(true, isPalindrome("*"))
    }

    @Test
    fun isPalindrome_emptyString_trueAsOutput() {
        assertEquals(true, isPalindrome(""))
    }
}