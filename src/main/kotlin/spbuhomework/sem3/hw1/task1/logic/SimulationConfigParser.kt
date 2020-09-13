package spbuhomework.sem3.hw1.task1.logic

import java.io.File
import java.io.InputStream

/**
 * This object receives a raw configuration file (File) and
 * returns a list of three sets of lists of argument(List<Set<List<String>>>).
 * Each set contains a number of argument lists for creating and configuration of the network.
 * Description of sets:
 * First set: computer set, contains numbers of computers and their OS
 * Second set: connection set, contains information about connections
 * Third set: infection set, contains information about which computers are infected
 */
object SimulationConfigParser {
    private const val COMMENT_SIGN = "//"
    private val COMPUTER_OS_REGEX = "\\d+ \\S+".toRegex()
    private val CONNECTED_COMPUTERS_REGEX = "\\d+ (\\d+,)*\\d+".toRegex()
    private val INFECTED_COMPUTER_REGEX = "\\d+ \\S+".toRegex()

    private const val COMPUTER_OS_PARAGRAPH_NUMBER = 0
    private const val CONNECTIONS_PARAGRAPH_NUMBER = 1
    private const val INFECTIONS_PARAGRAPH_NUMBER = 2

    fun parseConfig(networkConfig: File): List<Set<List<String>>> {
        val configAsLineList =
            getConfigAsLineList(
                networkConfig
            )

        val paragraphSize = configAsLineList[0].toIntOrNull()
            ?: throw IllegalArgumentException("A number was expected but \"${configAsLineList[0]}\" was met:")

        val myLineSetCreator =
            LineSetCreator(
                configAsLineList,
                paragraphSize
            )
        return listOf(
            myLineSetCreator.getLineSet(
                COMPUTER_OS_REGEX,
                "Invalid format for OS input: ",
                COMPUTER_OS_PARAGRAPH_NUMBER * paragraphSize + 1
            ),
            myLineSetCreator.getLineSet(
                CONNECTED_COMPUTERS_REGEX,
                "Invalid format for connections input: ",
                CONNECTIONS_PARAGRAPH_NUMBER * paragraphSize + 1
            ),
            myLineSetCreator.getLineSet(
                INFECTED_COMPUTER_REGEX,
                "Invalid format for infection input: ",
                INFECTIONS_PARAGRAPH_NUMBER * paragraphSize + 1
            )
        )
    }

    private class LineSetCreator(val networkConfigLineList: List<String>, private val paragraphSize: Int){
        fun getLineSet(
            matchesTo: Regex,
            exceptionText: String,
            start: Int
        ): Set<List<String>> {
            val lineSet = mutableSetOf<List<String>>()
            for (i in start..start + paragraphSize) {
                val line = networkConfigLineList[i]
                require(line.matches(matchesTo)) { exceptionText + line }
                lineSet.add(line.split(" "))
            }
            return lineSet.toSet()
        }
    }


    private fun getConfigAsLineList(networkConfig: File): List<String> {
        val inputStream: InputStream = networkConfig.inputStream()
        val configAsLineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine {
            if (!it.startsWith(COMMENT_SIGN) && it.isNotBlank()) {
                configAsLineList.add(it)
            }
        }
        return configAsLineList
    }
}

