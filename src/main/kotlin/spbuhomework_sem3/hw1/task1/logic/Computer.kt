package spbuhomework_sem3.hw1.task1.logic

import kotlin.random.Random

open class Computer(
    private val securityFactor: Double,
    val turnsUntilCheck: Int,
    private val findingFactor: Double,
    private val nameOfOS: String,
    private val ID: Int
) {
    private val viruses: MutableList<Virus> = mutableListOf()
    private val connectedComputers: MutableList<Computer> = mutableListOf()

    private var turnsUntilCheckLeft = turnsUntilCheck

    private fun transferVirus(subComputer: Computer, virus: Virus) {
        val finalChanceOfInfection = virus.infectivity * (1 - subComputer.securityFactor)

        if (Random.nextDouble(from = 0.0, until = 1.0) <= finalChanceOfInfection
            && subComputer.viruses.find { it.name == virus.name } == null
        ) {
            subComputer.viruses.add(virus)
        }
    }

    override fun toString(): String {
        return listOf(
            ID.toString(),
            nameOfOS,
            viruses.joinToString(",") { it.toString() }).joinToString("\t")
    }

    fun spreadVirusPhase() {
        for (computer in connectedComputers) {
            for (virus in viruses) {
                transferVirus(computer, virus)
            }
        }
    }

    fun checkPhase() {
        if (turnsUntilCheckLeft > 0) {
            turnsUntilCheckLeft--
        } else {
            for (virus in viruses) {
                val finalChanceOfFinding = virus.symptomatic * findingFactor

                if (Random.nextDouble(from = 0.0, until = 1.0) <= finalChanceOfFinding) {
                    viruses.remove(virus)
                }
            }
        }
    }
}