package spbuhomework.sem3.hw3

import java.util.concurrent.atomic.AtomicInteger

const val NUMBER_OF_PARKING_SPACES = 10

class MainServer(val numOfParkingSpaces: Int = NUMBER_OF_PARKING_SPACES) {
    val availableSpaces = AtomicInteger(numOfParkingSpaces)
}
