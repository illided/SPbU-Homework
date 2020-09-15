package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Virus

class InfectiveButSymptomaticVirus :
    Virus(
        infectivity = 1.0,
        symptomatic = 1.0,
        name = "InfectiveVirus"
    )

class InfectiveAndStealthyVirus :
    Virus(
        infectivity = 1.0,
        symptomatic = 0.0,
        name = "UltraVirus"
    )

class UselessVirus :
    Virus(
        infectivity = 0.0,
        symptomatic = 1.0,
        name = "Useless"
    )

class SemiInfectiveSemiStealthyVirus :
    Virus(
        infectivity = 0.5,
        symptomatic = 0.5,
        name = "SemiVirus"
    )
