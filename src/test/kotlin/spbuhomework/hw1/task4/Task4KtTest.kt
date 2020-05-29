package spbuhomework.hw1.task4

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Task4KtTest {

    @Test
    fun isPalindrome_palindromeStringInput_trueAsOutput() {
        assertTrue(isPalindrome("racecar"))
    }

    @Test
    fun isPalindrome_longerPalindromeStringInput_trueAsOutput() {
        assertTrue(isPalindrome("Abbbba ded abbbbA"))
    }

    @Test
    fun isPalindrome_nonPalindromeStringInput_falseAsOutput() {
        assertFalse(isPalindrome("Kotlin"))
    }

    @Test
    fun isPalindrome_longerNonPalindromeStringInput_falseAsOutput() {
        assertFalse(isPalindrome("This string is not a palindrome"))
    }

    @Test
    fun isPalindrome_oneCharacterInput_trueAsOutput() {
        assertTrue(isPalindrome("*"))
    }

    @Test
    fun isPalindrome_emptyString_trueAsOutput() {
        assertTrue(isPalindrome(""))
    }
}