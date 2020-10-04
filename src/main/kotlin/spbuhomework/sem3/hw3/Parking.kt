package spbuhomework.sem3.hw3

class Parking(private val server: MainServer) {
    fun tryToEnter(): Boolean = server.send(Request("enter"))
    fun leave(): Boolean = server.send(Request("leave"))
}
