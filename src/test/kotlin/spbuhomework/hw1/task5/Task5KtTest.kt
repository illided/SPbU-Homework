package spbuhomework.hw1.task5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.FileNotFoundException

private const val TEST_FILE_PATH = "src/test/kotlin/spbuhomework/hw1/task5/"

internal class Task5KtTest {

    @Test
    fun countNonBlanks_emptyFile_expectZero() {
        assertEquals(0, countNonBlanks(TEST_FILE_PATH + "emptyFileTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithSpacesAndText_correctOutput() {
        assertEquals(2, countNonBlanks(TEST_FILE_PATH + "spacesTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithTabsAndText_correctOutput() {
        assertEquals(2, countNonBlanks(TEST_FILE_PATH + "tabsTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithTabsSpacesAndText_correctOutput() {
        assertEquals(2, countNonBlanks(TEST_FILE_PATH + "tabsPlusSpaces.txt"))
    }

    @Test
    fun countNonBlanks_nonExistingFile_exceptionReceived() {
        assertThrows(FileNotFoundException::class.java) {
            countNonBlanks(TEST_FILE_PATH + "nonExistingFile.txt")
        }
    }
}