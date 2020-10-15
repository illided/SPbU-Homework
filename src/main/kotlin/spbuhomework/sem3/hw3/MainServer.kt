package spbuhomework.sem3.hw3

import java.util.concurrent.atomic.AtomicInteger

const val NUMBER_OF_PARKING_SPACES = 10

class Database(private val numOfParkingSpaces: Int, startingAvailableSpaces: Int) {
    private val availableSpaces = AtomicInteger(startingAvailableSpaces)

    fun proceedEntering(): Boolean {
        val valueBeforeEntering = availableSpaces.getAndUpdate {
            if (it - 1 >= 0) {
                return@getAndUpdate it - 1
            }
            return@getAndUpdate it
        }
        return valueBeforeEntering != 0
    }

    fun proceedLeaving(): Boolean {
        val valueBeforeLeaving = availableSpaces.getAndUpdate {
            if (it + 1 <= numOfParkingSpaces) {
                return@getAndUpdate it + 1
            }
            return@getAndUpdate it
        }
        return valueBeforeLeaving != numOfParkingSpaces
    }
}

class MainServer(
    private val numOfParkingSpaces: Int = NUMBER_OF_PARKING_SPACES,
    startingAvailableSpaces: Int = numOfParkingSpaces
) {
    private val database = Database(numOfParkingSpaces, startingAvailableSpaces)
    fun processRequest(request: Request): Boolean {
        var result = false
        val job = Thread {
            result = when (request.type) {
                "enter" -> database.proceedEntering()
                "leave" -> database.proceedLeaving()
                else -> false
            }
        }
        job.start()
        job.join()
        return result
    }
}
