package spbuhomework.sem3.hw1.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ComputerTest {

    @Test
    fun spreadVirusPhase_effectiveVirusToVulnerableComputer_infectionOccurred() {
        val contagious = VulnerableComputer(1)
        val clear = VulnerableComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(InfectiveButSymptomaticVirus())
        contagious.spreadVirusPhase()
        assertEquals(1, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_effectiveVirusToSecureComputer_infectionNotOccurred() {
        val contagious = VulnerableComputer(1)
        val clear = SecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(InfectiveButSymptomaticVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_weakVirusToVulnerableComputer_infectionNotOccurred(){
        val contagious = VulnerableComputer(1)
        val clear = VulnerableComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(UselessVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun checkPhase_symptomaticVirusSecureComputer_virusDefeated() {
        val myComputer = SecureComputer(1)
        myComputer.viruses.add(InfectiveButSymptomaticVirus())
        myComputer.checkPhase()
        assertEquals(0, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_symptomaticVirusVulnerableComputer_virusStayed() {
        val myComputer = VulnerableComputer(1)
        myComputer.viruses.add(InfectiveButSymptomaticVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_stealthyVirusSecureComputer_virusStayed() {
        val myComputer = SecureComputer(1)
        myComputer.viruses.add(InfectiveAndStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_stealthyVirusVulnerableComputer_virusStayed() {
        val myComputer = VulnerableComputer(1)
        myComputer.viruses.add(InfectiveAndStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }
}
