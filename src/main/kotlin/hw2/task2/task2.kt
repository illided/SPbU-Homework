package sbpuhomework.hw2.task2

inline fun <reified T : Comparable<T>> processArray(inputArray: Array<T>): Array<Any> {
    val outputArray = Array(inputArray.size) { inputArray[0] }
    var wasAdded = 0
    for (i in inputArray.indices) {
        if (!inputArray.drop(i + 1).contains(inputArray[i])) {
            outputArray[wasAdded] = inputArray[i]
            wasAdded++
        }
    }
    return outputArray.dropLast(inputArray.size - wasAdded).toTypedArray()
}

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    println("Enter the length of the array:")
    val myArray = Array<Int>(scan.nextInt()) { 0 }
    println(
        "Enter the array:\n" +
                "(Through the console, you can enter only an array of numbers,\n" +
                "however, a function that solves this problem is able to work\n" +
                "with any types for which \"equals\" is defined)"
    )
    for (i in myArray.indices) {
        myArray[i] = scan.nextInt()
    }
    println("Answer:")
    for (element in processArray(myArray)) {
        print(element)
        print(" ")
    }
}