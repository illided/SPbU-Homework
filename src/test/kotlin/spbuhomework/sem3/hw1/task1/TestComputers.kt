package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer

data class VulnerableComputer(val id: Int) :
    Computer(
        ID = id,
        securityFactor = 0.0,
        findingFactor = 0.0,
        turnsUntilCheck = 10,
        nameOfOS = "Vulnerable"
    )

data class SecureComputer(val id: Int) :
    Computer(
        ID = id,
        securityFactor = 1.0,
        findingFactor = 0.0,
        turnsUntilCheck = 10,
        nameOfOS = "Secure"
    )