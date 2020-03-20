package hw1.task1

fun splitAndRejoin(m: Int, n: Int, input: IntArray): Array<Int>? {
    if (m < 0 || n < 0) {
        return null
    }
    if (m + n != input.size) {
        return null
    }
    val firstPart = Array(m) { i -> input[i] }.reversedArray()
    val secondPart = Array(n) { i -> input[i + m] }.reversedArray()
    return (firstPart + secondPart).reversedArray()
}

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    println("Enter m and n:")
    val m = scan.nextInt()
    val n = scan.nextInt()
    val myArray = IntArray(m + n) { 0 }
    println(
        "Enter the array of int with the length of ${m + n} " +
                "(other numbers will be ignored)"
    )
    for (i in 0 until m + n) {
        myArray[i] = scan.nextInt()
    }
    val answer = splitAndRejoin(m, n, myArray)
    if (answer != null) {
        print("Answer: " + answer.joinToString())
    } else {
        print("Something went wrong")
    }
}