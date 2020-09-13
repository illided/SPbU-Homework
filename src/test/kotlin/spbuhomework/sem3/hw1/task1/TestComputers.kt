package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer

class VulnerableComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 0.0,
        findingFactor = 0.0,
        turnsUntilCheck = 10,
        nameOfOS = "Vulnerable"
    )

class SecureComputer(id: Int) :
    Computer(
        id = id,
        securityFactor = 1.0,
        findingFactor = 0.0,
        turnsUntilCheck = 10,
        nameOfOS = "Secure"
    )