package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Computer
import spbuhomework.sem3.hw1.task1.logic.ProbabilityRandomizer
import spbuhomework.sem3.hw1.task1.logic.StandardRandomizer

class VulnerableComputer(id: Int, randomizer: ProbabilityRandomizer = StandardRandomizer()) :
    Computer(
        id = id,
        securityFactor = 0.0,
        findingFactor = 0.0,
        turnsUntilCheck = 1,
        nameOfOS = "Vulnerable",
        randomizer = randomizer
    )

class SecureComputer(id: Int, randomizer: ProbabilityRandomizer = StandardRandomizer()) :
    Computer(
        id = id,
        securityFactor = 1.0,
        findingFactor = 1.0,
        turnsUntilCheck = 1,
        nameOfOS = "Secure",
        randomizer = randomizer
    )

class SemiSecureComputer(id: Int, randomizer: ProbabilityRandomizer = StandardRandomizer()) :
        Computer(
            id = id,
            securityFactor = 0.5,
            findingFactor = 0.5,
            turnsUntilCheck = 1,
            nameOfOS = "SemiSecure",
            randomizer = randomizer
        )