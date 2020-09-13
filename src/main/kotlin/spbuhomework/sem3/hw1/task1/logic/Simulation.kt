package spbuhomework.sem3.hw1.task1.logic

import java.io.File
import java.lang.IllegalArgumentException

class Simulation(networkConfig: File) {

   /* private val network: Set<Computer>
    init {
        network = Builder.buildNetwork(networkConfig)
    }

    internal object Builder {
        fun buildNetwork(networkConfig: File): Set<Computer> {

            try {
                val (computerLineSet,
                    connectionLineSet,
                    infectionLineSet) = SimulationConfigParser.parseConfig(networkConfig)

                val network = createNetwork(computerLineSet)

            } catch (exception: IllegalArgumentException) {

            }
            *//*val inputStream: InputStream = networkConfig.inputStream()
            val configAsLineList = mutableListOf<String>()

            inputStream.bufferedReader().forEachLine { configAsLineList.add(it) }
            //need to add try catch block
            val network: Set<Computer> = createNetwork(configAsLineList)
            setConnections(configAsLineList, network)*//*

            return network
        }

        private fun createNetwork(networkConfigLineSet: Set<List<String>>): Set<Computer> {

        }

        private fun setConnections(networkConfigLineSet: List<String>, network: Set<Computer>) {

        }

        private fun setInfection(networkConfigLineSet: List<String>, network: Set<Computer>) {

        }
    }*/

    fun runSimulation() {

    }

}

