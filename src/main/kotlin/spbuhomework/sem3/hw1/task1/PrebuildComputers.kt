package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer

class WindowsProtectedComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 0.7,
        turnsUntilCheck = 1,
        findingFactor = 0.8,
        nameOfOS = "Windows[Protected]"
    )

class WindowsUnprotectedComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 0.2,
        turnsUntilCheck = 3,
        findingFactor = 0.5,
        nameOfOS = "Windows[Unprotected]"
    )

class LinuxComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 0.8,
        turnsUntilCheck = 5,
        findingFactor = 0.8,
        nameOfOS = "Linux"
    )

class MacComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 0.9,
        turnsUntilCheck = 10,
        findingFactor = 0.7,
        nameOfOS = "MacOS"
    )
