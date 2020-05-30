package spbuhomework.hw2.task2

inline fun <reified T> leaveTheFirstRightInclusions(inputArray: Array<T>): Array<T> where T: Comparable<T>{
    return inputArray.reversed().toSet().reversed().toTypedArray()
}

fun main() {
    println("Enter the length of the array:")
    println(
        "Enter the array:\n" +
                "(Through the console, you can enter only an array of numbers,\n" +
                "however, a function that solves this problem is able to work\n" +
                "with any types for which \"equals\" is defined)"
    )
    val myArray = readLine()?.split(" ")?.map { it.toInt() }?.toTypedArray()
    if (myArray != null) {
        println("Answer: " + leaveTheFirstRightInclusions(myArray).joinToString(" "))
    } else {
        println("Wrong input")
    }
}
