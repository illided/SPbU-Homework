package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.Virus

class CryptoLockerVirus :
    Virus(
        infectivity = 0.3,
        symptomatic = 0.5,
        name = "CryptoLocker"
    )

class WannaCryVirus :
    Virus(
        infectivity = 0.7,
        symptomatic = 0.9,
        name = "WannaCry"
    )
