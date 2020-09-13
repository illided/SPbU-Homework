package spbuhomework.sem3.hw1.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import spbuhomework.sem3.hw1.task1.logic.SimulationConfigParser
import java.io.File

const val TEST_FILES_PATH = "src/test/resources/spbuhomework/sem3/hw1/task1/"

internal class SimulationConfigParserTest {

    @Test
    fun parseConfig_correctConfig_correctNumOfOsLines() {
        val correctConfig = File(TEST_FILES_PATH + "3_computers_correct_config.txt")
        val (computerLineSet,
            connectionLineSet,
            infectionLineSet) = SimulationConfigParser.parseConfig(correctConfig)
        assertEquals(3, computerLineSet.size)
    }

    @Test
    fun parseConfig_correctConfig_correctNumOfConnectionLines() {
        val correctConfig = File(TEST_FILES_PATH + "3_computers_correct_config.txt")
        val (computerLineSet,
            connectionLineSet,
            infectionLineSet) = SimulationConfigParser.parseConfig(correctConfig)
        assertEquals(3, connectionLineSet.size)
    }

    @Test
    fun parseConfig_correctConfig_correctNumOfInfectionLines() {
        val correctConfig = File(TEST_FILES_PATH + "3_computers_correct_config.txt")
        val (computerLineSet,
            connectionLineSet,
            infectionLineSet) = SimulationConfigParser.parseConfig(correctConfig)
        assertEquals(2, infectionLineSet.size)
    }

    @Test
    fun parseConfig_wrongOSConfiguration_exceptionThrown() {
        val wrongConfig = File(TEST_FILES_PATH + "3_computers_wrong_OS_config.txt")
        assertThrows(IllegalArgumentException::class.java){
            val (computerLineSet,
                connectionLineSet,
                infectionLineSet) = SimulationConfigParser.parseConfig(wrongConfig)
        }
    }

    @Test
    fun parseConfig_wrongConnectionConfiguration_exceptionThrown() {
        val wrongConfig = File(TEST_FILES_PATH + "3_computers_wrong_connections_config.txt")
        assertThrows(IllegalArgumentException::class.java){
            val (computerLineSet,
                connectionLineSet,
                infectionLineSet) = SimulationConfigParser.parseConfig(wrongConfig)
        }
    }
    @Test
    fun parseConfig_wrongInfectionsConfiguration_exceptionThrown() {
        val wrongConfig = File(TEST_FILES_PATH + "3_computers_wrong_infections_config.txt")
        assertThrows(IllegalArgumentException::class.java){
            val (computerLineSet,
                connectionLineSet,
                infectionLineSet) = SimulationConfigParser.parseConfig(wrongConfig)
        }
    }

    @Test
    fun parseConfig_emptyConfig_exceptionThrown(){
        val emptyConfig = File(TEST_FILES_PATH + "empty_config.txt")
        assertThrows(IllegalArgumentException::class.java){
            val (computerLineSet,
                connectionLineSet,
                infectionLineSet) = SimulationConfigParser.parseConfig(emptyConfig)
        }
    }

}