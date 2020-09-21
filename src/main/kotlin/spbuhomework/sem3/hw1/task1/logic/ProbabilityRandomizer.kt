package spbuhomework.sem3.hw1.task1.logic

import kotlin.random.Random

interface ProbabilityRandomizer {
    fun getProbabilityCheck(): Double
}

class StandardRandomizer : ProbabilityRandomizer {
    override fun getProbabilityCheck() = Random.nextDouble(from = 0.0, until = 1.0)
}
