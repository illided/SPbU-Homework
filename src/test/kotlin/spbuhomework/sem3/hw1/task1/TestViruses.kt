package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Virus

class InfectiveButSymptomaticVirus :
    Virus(
        infectivity = 0.4,
        symptomatic = 0.4,
        name = "InfectiveVirus"
    )

class InfectiveAndStealthyVirus:
    Virus(
        infectivity = 0.4,
        symptomatic = 0.4,
        name = "UltraVirus"
    )
