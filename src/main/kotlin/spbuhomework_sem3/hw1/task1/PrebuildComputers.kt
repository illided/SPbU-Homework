package spbuhomework_sem3.hw1.task1

import spbuhomework_sem3.hw1.task1.logic.Computer

class WindowsProtectedComputer(id: Int) :
    Computer(
        ID = id,
        securityFactor = 0.5,
        turnsUntilCheck = 10,
        findingFactor = 0.8,
        nameOfOS = "Windows[Protected]"
    )

class WindowsUnprotectedComputer(id: Int) :
    Computer(
        ID = id,
        securityFactor = 0.1,
        turnsUntilCheck = 5,
        findingFactor = 0.5,
        nameOfOS = "Windows[Unprotected]"
    )

class LinuxComputer(id: Int) :
    Computer(
        ID = id,
        securityFactor = 0.7,
        turnsUntilCheck = 15,
        findingFactor = 0.7,
        nameOfOS = "Linux"
    )

class MacComputer(id: Int) :
    Computer(
        ID = id,
        securityFactor = 0.9,
        turnsUntilCheck = 20,
        findingFactor = 0.6,
        nameOfOS = "MacOS"
    )
