package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Simulation
import java.io.File

const val CONFIG_OF_NETWORK_PATH = "src/main/kotlin/spbuhomework/sem3/hw1/task1/ConfigurationOfNetwork.txt"

object NetworkSetUp {
    const val NUM_OF_TURNS = 100
    const val LENGTH_OF_TURN = 1000
    const val SHOW_STATISTIC_EVERY_x_TURNS = 1
}

fun main() {
    val config = File(CONFIG_OF_NETWORK_PATH)
    if (!config.exists()) {
        print("No access to config file")
    } else {
        try {
            val mySimulation = Simulation(config)
            mySimulation.run()
        } catch (exception: IllegalArgumentException) {
            print(exception.message)
        }
    }
}