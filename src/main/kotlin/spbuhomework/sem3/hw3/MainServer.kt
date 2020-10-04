package spbuhomework.sem3.hw3

import java.util.concurrent.atomic.AtomicInteger

const val NUMBER_OF_PARKING_SPACES = 10

class MainServer(
    private val numOfParkingSpaces: Int = NUMBER_OF_PARKING_SPACES,
    startingAvailableSpaces: Int = numOfParkingSpaces
) {
    private val availableSpaces = AtomicInteger(startingAvailableSpaces)
    fun send(request: Request): Boolean {
        return when (request.type) {
            "enter" -> proceedEntering()
            "leave" -> proceedLeaving()
            else -> false
        }
    }

    private fun proceedEntering(): Boolean {
        val valueBeforeEntering = availableSpaces.getAndUpdate {
            if (it - 1 >= 0) {
                return@getAndUpdate it - 1
            }
            return@getAndUpdate it
        }
        return valueBeforeEntering != 0
    }

    private fun proceedLeaving(): Boolean {
        val valueBeforeLeaving = availableSpaces.getAndUpdate {
            if (it + 1 <= numOfParkingSpaces) {
                return@getAndUpdate it + 1
            }
            return@getAndUpdate it
        }
        return valueBeforeLeaving != numOfParkingSpaces
    }
}
