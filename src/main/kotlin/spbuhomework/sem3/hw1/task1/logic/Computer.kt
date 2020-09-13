package spbuhomework.sem3.hw1.task1.logic

import kotlin.random.Random

open class Computer(
    private val securityFactor: Double,
    private val turnsUntilCheck: Int,
    private val findingFactor: Double,
    private val nameOfOS: String,
    val id: Int
) {
    init {
        require(securityFactor in 0.0..1.0) { "Invalid security factor (must be between 1 and 0" }
        require(turnsUntilCheck > 0) { "Invalid num of turns until next check (must be greater than 0)" }
        require(findingFactor in 0.0..1.0) { "Invalid finding factor (must be between 1 and 0" }
        require(id > 0) { "Invalid ID (must be greater than 0" }
    }

    val viruses: MutableList<Virus> = mutableListOf()
    val connectedComputers: MutableList<Computer> = mutableListOf()

    private var turnsUntilCheckLeft = turnsUntilCheck

    private fun transferVirus(subComputer: Computer, virus: Virus) {
        val finalChanceOfInfection = virus.infectivity * (1 - subComputer.securityFactor)

        if (Random.nextDouble(from = 0.0, until = 1.0) <= finalChanceOfInfection &&
            subComputer.viruses.find { it.name == virus.name } == null
        ) {
            subComputer.viruses.add(virus)
        }
    }

    override fun toString(): String {
        return listOf(
            id.toString(),
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
        turnsUntilCheckLeft--
        if (turnsUntilCheckLeft <= 0) {
            val quarantine = mutableListOf<Virus>()
            for (virus in viruses) {
                val finalChanceOfFinding = virus.symptomatic * findingFactor

                if (Random.nextDouble(from = 0.0, until = 1.0) <= finalChanceOfFinding) {
                    quarantine.add(virus)
                }
            }
            viruses.removeAll(quarantine)
            turnsUntilCheckLeft = turnsUntilCheck
        }
    }
}
