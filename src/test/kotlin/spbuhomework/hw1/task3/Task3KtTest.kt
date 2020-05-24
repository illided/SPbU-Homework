package spbuhomework.hw1.task3

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class Task3KtTest {

    @Test
    fun getNumberOfInclusions_mainStringContainSubString_correctOutput() {
        assertEquals(2, getNumberOfInclusions("Hello Iceland and Greenland", "land"))
    }

    @Test
    fun getNumberOfInclusions_mainStringContainSubStringBigInput_correctOutput() {
        assertEquals(
            7, getNumberOfInclusions(
                "Alice was beginning to get very tired of sitting by her " +
                        "sister on the bank, and of having nothing to do: once or " + //1 here
                        "twice she had peeped into the book her sister was reading, " + //1 here
                        "but it had no pictures or conversations in it, “and what " +
                        "is the use of a book,” thought Alice “without pictures or " + //1 here
                        "conversations?”\n" +
                        "\n" +
                        "So she was considering in her own mind (as well as she could, " +
                        "for the hot day made her feel very sleepy and stupid), whether " + //1 here
                        "the pleasure of making a daisy-chain would be worth the " + //2 here
                        "trouble of getting up and picking the daisies, when suddenly " + //and 1 her
                        "a White Rabbit with pink eyes ran close by her.",
                " the "
            )
        )
    }

    @Test
    fun getNumberOfInclusions_mainStringDontContainSubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Zachtite dz pozhaluista", "net"))
    }

    @Test
    fun getNumberOfInclusions_mainStringDontContainSubStringBigInput_zeroOutput() {
        assertEquals(
            0, getNumberOfInclusions(
                "There was nothing so very remarkable in that; nor did " +
                        "Alice think it so very much out of the way to hear the " +
                        "Rabbit say to itself, “Oh dear! Oh dear! I shall be late!” " +
                        "(when she thought it over afterwards, it occurred to her " +
                        "that she ought to have wondered at this, but at the time " +
                        "it all seemed quite natural); but when the Rabbit actually " +
                        "took a watch out of its waistcoat-pocket, and looked at it, " +
                        "and then hurried on, Alice started to her feet, for it flashed " +
                        "across her mind that she had never before seen a rabbit with " +
                        "either a waistcoat-pocket, or a watch to take out of it, and " +
                        "burning with curiosity, she ran across the field after it, " +
                        "and fortunately was just in time to see it pop down a large " +
                        "rabbit-hole under the hedge.",
                "bad story"
            )
        )
    }

    @Test
    fun getNumberOfInclisions_differentLanguageBetweenMainAndSubString_zeroAsOutput() {
        assertEquals(
            0, getNumberOfInclusions(
                "In another moment down went Alice after it, never once " +
                        "considering how in the world she was to get out again.", "Алиса"
            )
        )
    }

    @Test
    fun getNumberOfInclusions_mainStringShorterThanSubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Life", "University"))
    }

    @Test
    fun getNumberOfInclusions_emptyMainString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("", "Orange"))
    }

    @Test
    fun getNumberOfInclusions_emptySubString_zeroOutput() {
        assertEquals(0, getNumberOfInclusions("Hello", ""))
    }

    @Test
    fun getNumberOfInclusiond_mainStringAndSubStringAreBothEmpty_oneAsOutput() {
        assertEquals(1, getNumberOfInclusions("", ""))
    }
}