package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer

object ComputerGiver {
    fun getComputer(id: Int, ComputerOS: String): Computer {
        return when (ComputerOS) {
            "Windows[Protected]" -> WindowsProtectedComputer(id)

            else -> throw IllegalArgumentException("This OS does not exist: $ComputerOS")
        }
    }
}