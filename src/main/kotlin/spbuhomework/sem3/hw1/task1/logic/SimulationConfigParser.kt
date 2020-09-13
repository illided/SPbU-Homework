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

        require(configAsLineList.isNotEmpty()) {"Config file is empty or all text is commented/blank"}
        val paragraphSize = configAsLineList[0].toIntOrNull()
            ?: throw IllegalArgumentException("A number was expected but \"${configAsLineList[0]}\" was met:")

        val myLineSetCreator = LineSetCreator(configAsLineList, paragraphSize)
        return listOf(
            myLineSetCreator.getLineSet(
                lineMatchesTo = COMPUTER_OS_REGEX,
                exceptionText = "Invalid format for OS input: ",
                start = COMPUTER_OS_PARAGRAPH_NUMBER * paragraphSize + 1,
                stop = (COMPUTER_OS_PARAGRAPH_NUMBER + 1) * paragraphSize + 1
            ),
            myLineSetCreator.getLineSet(
                lineMatchesTo = CONNECTED_COMPUTERS_REGEX,
                exceptionText = "Invalid format for connections input: ",
                start = CONNECTIONS_PARAGRAPH_NUMBER * paragraphSize + 1,
                stop = (CONNECTIONS_PARAGRAPH_NUMBER + 1) * paragraphSize + 1
            ),
            myLineSetCreator.getLineSet(
                lineMatchesTo = INFECTED_COMPUTER_REGEX,
                exceptionText = "Invalid format for infection input: ",
                start = INFECTIONS_PARAGRAPH_NUMBER * paragraphSize + 1,
                stop = configAsLineList.size
            )
        )
    }

    private class LineSetCreator(val networkConfigLineList: List<String>, private val paragraphSize: Int) {
        fun getLineSet(
            lineMatchesTo: Regex,
            exceptionText: String,
            start: Int,
            stop: Int
        ): Set<List<String>> {
            val lineSet = mutableSetOf<List<String>>()
            for (i in start until stop) {
                val line = networkConfigLineList[i]
                require(line.matches(lineMatchesTo)) { exceptionText + line }
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
