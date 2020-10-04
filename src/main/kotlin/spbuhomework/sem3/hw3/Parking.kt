package spbuhomework.sem3.hw3

class Parking(private val server: MainServer) {
    private val mySender = Sender(server)
    fun tryToEnter(): Boolean = mySender.sendRequest(Request("enter"))
    fun leave(): Boolean = mySender.sendRequest(Request("leave"))
}

class Sender(private val server: MainServer) {
    fun sendRequest(request: Request): Boolean {
        var result = false
        val job = Thread {
            result = server.send(request)
        }
        job.run()
        job.join()
        return result
    }
}
