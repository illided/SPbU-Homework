package spbuhomework.sem3.hw1.task1

import spbuhomework.sem3.hw1.task1.logic.ProbabilityRandomizer

class MockDoubleRandomizer(private val doubleToReturn: Double) : ProbabilityRandomizer {
    override fun getProbabilityCheck(): Double = doubleToReturn
}