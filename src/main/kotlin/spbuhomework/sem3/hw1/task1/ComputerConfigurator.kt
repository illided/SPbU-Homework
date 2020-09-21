package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer

object ComputerConfigurator {
    fun getComputer(id: Int, computerOS: String): Computer {
        return when (computerOS) {
            "Windows[Protected]" -> WindowsProtectedComputer(id)
            "Windows[Unprotected]" -> WindowsUnprotectedComputer(id)
            "Linux" -> LinuxComputer(id)
            "MacOS" -> MacComputer(id)
            else -> throw IllegalArgumentException("This OS does not exist: $computerOS")
        }
    }

    fun addVirus(computer: Computer, virusString: String) {
        when (virusString) {
            "WannaCry" -> computer.viruses.add(WannaCryVirus())
            "CryptoLocker" -> computer.viruses.add(CryptoLockerVirus())
            else -> throw IllegalArgumentException("This virus does not exist: $virusString")
        }
    }
}
