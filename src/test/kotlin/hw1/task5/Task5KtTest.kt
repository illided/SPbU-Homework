import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.FileNotFoundException

internal class Task5KtTest {

    @Test
    fun countNonBlanks_emptyFile_expectZero() {
        assertEquals(0, countNonBlanks("src/test/kotlin/hw1/task5/emptyFileTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithSpacesAndText_correctOutput() {
        assertEquals(2, countNonBlanks("src/test/kotlin/hw1/task5/spacesTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithTabsAndText_correctOutput() {
        assertEquals(2, countNonBlanks("src/test/kotlin/hw1/task5/tabsTest.txt"))
    }

    @Test
    fun countNonBlanks_fileWithTabsSpacesAndText_correctOutput() {
        assertEquals(2, countNonBlanks("src/test/kotlin/hw1/task5/tabsPlusSpaces.txt"))
    }

    @Test
    fun countNonBlanks_nonExistingFile_exceptionReceived() {
        assertThrows(FileNotFoundException::class.java) {
            countNonBlanks("src/test/kotlin/hw1/task5/nonExistingFile.txt")
        }
    }
}