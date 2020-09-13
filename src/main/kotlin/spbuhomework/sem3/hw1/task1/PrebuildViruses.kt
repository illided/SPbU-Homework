package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Virus

class CryptoLockerVirus :
    Virus(
        infectivity = 0.4,
        symptomatic = 0.4,
        name = "CryptoLocker"
    )

class WannaCryVirus :
    Virus(
        infectivity = 0.8,
        symptomatic = 0.4,
        name = "WannaCry"
    )