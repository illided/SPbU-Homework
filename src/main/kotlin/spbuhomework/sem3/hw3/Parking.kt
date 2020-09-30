package spbuhomework.sem3.hw3

class Parking(private val server: MainServer) {
    fun tryToEnter(): Boolean {
        val valueBeforeEntering = server.availableSpaces.getAndUpdate {
            if (it - 1 >= 0) {
                return@getAndUpdate it - 1
            }
            return@getAndUpdate it
        }
        return valueBeforeEntering != 0
    }

    fun leave(): Boolean {
        val valueBeforeLeaving = server.availableSpaces.getAndUpdate {
            if (it + 1 <= server.numOfParkingSpaces) {
                return@getAndUpdate it + 1
            }
            return@getAndUpdate it
        }
        return valueBeforeLeaving != server.numOfParkingSpaces
    }
}
