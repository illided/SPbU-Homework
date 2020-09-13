package spbuhomework.sem3.hw1.task1.logic

import spbuhomework.sem3.hw1.task1.ComputerConfigurator
import spbuhomework.sem3.hw1.task1.NetworkSetUp
import java.io.File
import kotlin.IllegalArgumentException

class Simulation(networkConfig: File) {

    private val network: Set<Computer>

    init {
        network = buildNetwork(networkConfig)
    }

    companion object Builder {
        fun buildNetwork(networkConfig: File): Set<Computer> {
            try {
                val (computerLineSet,
                    connectionLineSet,
                    infectionLineSet) = SimulationConfigParser.parseConfig(networkConfig)

                val network = createNetwork(computerLineSet)
                setConnections(connectionLineSet, network)
                setInfection(infectionLineSet, network)

                return network
            } catch (exception: IllegalArgumentException) {
                throw IllegalArgumentException("Mistake in the config file: ${exception.message}")
            }
        }

        private fun createNetwork(networkConfigLineSet: Set<List<String>>): Set<Computer> {
            val newNetwork = mutableSetOf<Computer>()
            for (line in networkConfigLineSet) {
                newNetwork.add(ComputerConfigurator.getComputer(line[0].toInt(), line[1]))
            }
            return newNetwork.toSet()
        }

        private fun setConnections(networkConfigLineSet: Set<List<String>>, network: Set<Computer>) {
            for (line in networkConfigLineSet) {
                val targetComputer = network.find { it.ID == line[0].toInt() }
                    ?: throw IllegalArgumentException("Can't find computer with ID: ${line[0]}")

                val connectedComputerList = line[1].split(",").map { idInTheLineSet ->
                    network.find { computerInTheNetwork -> computerInTheNetwork.ID == idInTheLineSet.toInt() }
                        ?: throw IllegalArgumentException("Can't find computer with ID: $idInTheLineSet")
                }
                for (computer in connectedComputerList) {
                    targetComputer.connectedComputers.add(computer)
                }
            }
        }

        private fun setInfection(networkConfigLineSet: Set<List<String>>, network: Set<Computer>) {
            for (line in networkConfigLineSet) {
                val targetComputer = network.find { it.ID == line[0].toInt() }
                    ?: throw IllegalArgumentException("Can't find computer with ID: ${line[0]}")
                ComputerConfigurator.addVirus(targetComputer, line[1])
            }
        }
    }

    fun run() {
        for (i in 1..NetworkSetUp.NUM_OF_TURNS) {
            if (i % NetworkSetUp.SHOW_STATISTIC_EVERY_x_TURNS == 0) {
                printStatistic()
            }
            makeTurn()
            Thread.sleep(NetworkSetUp.LENGTH_OF_TURN.toLong())
        }
    }

    private fun makeTurn() {
        //spread virus
        for (computer in network) {
            computer.spreadVirusPhase()
        }
        //then all computers check for viruses
        for (computer in network) {
            computer.checkPhase()
        }
    }

    private fun printStatistic() {
        print("--------------------------------------------------------\n")
        for (computer in network) {
            print("$computer\n")
        }
        print("--------------------------------------------------------\n")
    }
}

