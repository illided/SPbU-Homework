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
    fun spreadVirusPhase_weakVirusToVulnerableComputer_infectionNotOccurred() {
        val contagious = VulnerableComputer(1)
        val clear = VulnerableComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(UselessVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_weakVirusToSecureComputer_infectionNotOccurred() {
        val contagious = VulnerableComputer(1)
        val clear = SecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(UselessVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }


    @Test
    fun spreadVirusPhase_vulnerableComputerSemiInfectiveVirusLowMocking_infectionOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        val clear = VulnerableComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(1, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_vulnerableComputerSemiInfectiveVirusHighMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        val clear = VulnerableComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_secureComputerSemiInfectiveVirusLowMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        val clear = SecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_secureComputerSemiInfectiveVirusHighMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        val clear = SecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerInfectiveVirusLowMocking_infectionOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(InfectiveAndStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(1, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerInfectiveVirusHighMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(InfectiveAndStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerUselessVirusLowMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(UselessVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerUselessVirusHighMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(UselessVirus())
        contagious.spreadVirusPhase()
        assertEquals(0, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerSemiInfectiveVirusLowMocking_infectionOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
        contagious.spreadVirusPhase()
        assertEquals(1, clear.viruses.size)
    }

    @Test
    fun spreadVirusPhase_semiSecureComputerSemiInfectiveVirusHighMocking_infectionNotOccurred() {
        val contagious = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        val clear = SemiSecureComputer(2)
        contagious.connectedComputers.add(clear)
        contagious.viruses.add(SemiInfectiveSemiStealthyVirus())
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

    @Test
    fun checkPhase_semiStealthyVirusVulnerableComputerLowMocking_virusStayed() {
        val myComputer = VulnerableComputer(1, MockDoubleRandomizer(0.01))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_semiStealthyVirusVulnerableComputerHighMocking_virusStayed() {
        val myComputer = VulnerableComputer(1, MockDoubleRandomizer(0.99))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_semiStealthyVirusSecureComputerLowMocking_virusDefeated() {
        val myComputer = SecureComputer(1, MockDoubleRandomizer(0.01))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(0, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_semiStealthyVirusSecureComputerHighMocking_virusStayed() {
        val myComputer = SecureComputer(1, MockDoubleRandomizer(0.99))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_symptomaticVirusSemiSecureComputerLowMocking_virusDefeated() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.01))
        myComputer.viruses.add(InfectiveButSymptomaticVirus())
        myComputer.checkPhase()
        assertEquals(0, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_symptomaticVirusSemiSecureComputerHighMocking_virusStayed() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.99))
        myComputer.viruses.add(InfectiveButSymptomaticVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_uselessVirusSemiSecureComputerLowMocking_virusDefeated() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.01))
        myComputer.viruses.add(UselessVirus())
        myComputer.checkPhase()
        assertEquals(0, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_uselessVirusSemiSecureComputerHighMocking_virusStayed() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.99))
        myComputer.viruses.add(UselessVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_semiStealthyVirusSemiSecureComputerLowMocking_virusDefeated() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.01))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(0, myComputer.viruses.size)
    }

    @Test
    fun checkPhase_semiStealthyVirusSemiSecureComputerHighMocking_virusStayed() {
        val myComputer = SemiSecureComputer(1, MockDoubleRandomizer(0.99))
        myComputer.viruses.add(SemiInfectiveSemiStealthyVirus())
        myComputer.checkPhase()
        assertEquals(1, myComputer.viruses.size)
    }
}
