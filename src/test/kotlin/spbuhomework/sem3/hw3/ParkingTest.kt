package spbuhomework.sem3.hw3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ParkingTest {

    private fun createAndFillParking(numOfCars: Int, numOfParkingPlaces: Int): Parking {
        val myParkingMachine = Parking(MainServer(numOfParkingPlaces))
        for (i in 0 until numOfCars) {
            myParkingMachine.tryToEnter()
        }
        return myParkingMachine
    }

    @Test
    fun tryToEnter_threeCarsOneParkingMachineTenPlaces_carRegistered() {
        assertTrue(createAndFillParking(2, 10).tryToEnter())
    }

    @Test
    fun tryToEnter_threeCarsOneParkingMachineThreePlaces_carRegistered() {
        assertTrue(createAndFillParking(2, 3).tryToEnter())
    }

    @Test
    fun tryToEnter_ElevenCarsOneParkingMachineTenPlaces_carNotRegistered() {
        assertFalse(createAndFillParking(10, 10).tryToEnter())
    }

    @Test
    fun leave() {
    }
}