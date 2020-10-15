package spbuhomework.sem3.hw3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.concurrent.atomic.AtomicInteger

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
    fun tryToEnter_ThreeCarsTwoParkingMachinesThreePlacesNoAsync_carRegistered() {
        val myServer = MainServer(3)
        val firstParking = Parking(myServer)
        val secondParking = Parking(myServer)
        firstParking.tryToEnter()
        firstParking.tryToEnter()
        assertTrue(secondParking.tryToEnter())
    }

    @Test
    fun tryToEnter_ThreeCarsTwoParkingMachinesTwoPlacesNoAsync_carNotRegistered() {
        val myServer = MainServer(2)
        val firstParking = Parking(myServer)
        val secondParking = Parking(myServer)
        firstParking.tryToEnter()
        firstParking.tryToEnter()
        assertFalse(secondParking.tryToEnter())
    }

    private fun simulateEntering(
        numOfThreads: Int,
        carsPerThread: Int,
        numOfParkingPlaces: Int
    ): Int {
        val numOfEnteredCars = AtomicInteger(0)
        val listOfThreads = mutableListOf<Thread>()
        val myServer = MainServer(numOfParkingPlaces)
        for (i in 0 until numOfThreads) {
            val myParking = Parking(myServer)
            listOfThreads.add(Thread {
                for (j in 0 until carsPerThread) {
                    if (myParking.tryToEnter()) {
                        numOfEnteredCars.incrementAndGet()
                    }
                }
            })
        }
        listOfThreads.map { it.start() }
        listOfThreads.map { it.join() }
        return numOfEnteredCars.get()
    }

    @Test
    fun tryToEnter_ThreeCarsThreeParkingMachinesThreePlacesAsync_correctNumberOfEnteredCars() {
        assertEquals(3, simulateEntering(3, 1, 3))
    }

    @Test
    fun tryToEnter_ThreeCarsThreeParkingMachinesTwoPlacesAsync_correctNumberOfEnteredCars() {
        assertEquals(2, simulateEntering(3, 1, 2))
    }

    @Test
    fun tryToEnter_HundredCarsFiveParkingMachinesHundredPlacesAsync_correctNumberOfEnteredCars() {
        assertEquals(100, simulateEntering(5, 20, 100))
    }

    @Test
    fun tryToEnter_HundredCarsFiveParkingMachinesFiftyPlacesAsync_correctNumberOfEnteredCars() {
        assertEquals(50, simulateEntering(5, 20, 50))
    }

    private fun simulateLeaving(
        numOfThreads: Int,
        carsPerThread: Int,
        numOfParkingPlaces: Int,
        numOfParkedCars: Int
    ): Int {
        val numOfCarsLeft = AtomicInteger(0)
        val listOfThreads = mutableListOf<Thread>()
        val myServer = MainServer(numOfParkingPlaces, startingAvailableSpaces = numOfParkingPlaces - numOfParkedCars)
        for (i in 0 until numOfThreads) {
            val myParking = Parking(myServer)
            listOfThreads.add(Thread {
                for (j in 0 until carsPerThread) {
                    if (myParking.leave()) {
                        numOfCarsLeft.incrementAndGet()
                    }
                }
            })
        }
        listOfThreads.map { it.start() }
        listOfThreads.map { it.join() }
        return numOfCarsLeft.get()
    }

    @Test
    fun leave_fromNonEmptyParking_carLeft() {
        assertTrue(createAndFillParking(3, 5).leave())
    }

    @Test
    fun leave_fromEmptyParking_carNotLeft() {
        assertFalse(createAndFillParking(0, 5).leave())
    }

    @Test
    fun leave_hundredCarsLeaveFiveParkingMachinesHundredCarsParkedNoAsync_correctNumOfCarsThatLeft() {
        assertEquals(100, simulateLeaving(1, 100, 1000, 100))
    }

    @Test
    fun leave_hundredCarsLeaveFiveParkingMachinesFiftyCarsParkedNoAsync_correctNumOfCarsThatLeft() {
        assertEquals(50, simulateLeaving(1, 100, 1000, 50))
    }

    @Test
    fun leave_hundredCarsLeaveFiveParkingMachinesHundredCarsParkedAsync_correctNumOfCarsThatLeft() {
        assertEquals(100, simulateLeaving(5, 20, 1000, 100))
    }

    @Test
    fun leave_hundredCarsLeaveFiveParkingMachinesFiftyCarsParkedAsync_correctNumOfCarsThatLeft() {
        assertEquals(50, simulateLeaving(5, 20, 1000, 50))
    }
}
