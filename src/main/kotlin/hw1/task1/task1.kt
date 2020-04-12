package spbuhomework.hw1.task1

fun splitAndRejoin(m: Int, n: Int, input: IntArray): IntArray? {
    if (m < 0 || n < 0 || m + n != input.size) {
        throw IllegalArgumentException("Bounds does not match array length")
    }
    return (input.dropLast(n).reversed() + input.drop(m).reversed()).reversed().toIntArray()
}

fun main(args: Array<String>) {
    println("Enter m and n:")
    val m = readLine()?.toInt()
    val n = readLine()?.toInt()
    if (m == null || n == null) {
        println("Incorrect input for bounds")
        return
    }

    println(
        "Enter the array of int with the length of ${m + n} " +
                "(other numbers will be ignored)"
    )
    val myArray = readLine()?.split(" ")?.map { it.toInt() }?.toIntArray() ?: return

    var answer: IntArray? = null
    try {
        answer = splitAndRejoin(m, n, myArray)
    } catch (exception: IllegalArgumentException) {
        println(exception.message)
    }

    if (answer != null) {
        print("Answer: " + answer.joinToString())
    }
}
