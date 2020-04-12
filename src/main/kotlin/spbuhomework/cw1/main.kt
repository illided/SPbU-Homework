package spbuhomework.cw1

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    var command = -1
    val queue = PriorityQueue<Int>()
    println(
        "Hello! I am the priority queue. Here some things you can do:\n" +
                "1 - add number with the priority\n" +
                "2 - get top priority number\n" +
                "0 - exit"
    )

    while (command != 0) {
        println("Enter the command:")
        command = scan.nextInt()
        when (command) {
            1 -> {
                println("Enter the number and it's priority:")
                queue.enqueue(scan.nextInt(), scan.nextInt())
            }
            2 -> {
                try {
                    println("Top priority element: ${queue.dequeue()}")
                } catch (exception: IndexOutOfBoundsException) {
                    println("Priority queue is empty")
                }
            }
        }
    }
}
