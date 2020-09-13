package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Simulation
import java.io.File

const val CONFIG_OF_NETWORK_PATH = "src/main/kotlin/spbuhomework_sem3/hw1/task1/ConfigurationOfNetwork.txt"

const val MAX_NUM_OF_TURNS = 100
const val LENGTH_OF_TURN = 1000
const val SHOW_STATISTIC_EVERY_x_TURNS = 1
const val STOP_WHEN_NO_VIRUSES = true

fun main() {
    val config = File(CONFIG_OF_NETWORK_PATH)
    if (!config.exists()){
        print("No access to config file")
    } else {
        val mySimulation = Simulation(config)
        mySimulation.runSimulation()
    }
}