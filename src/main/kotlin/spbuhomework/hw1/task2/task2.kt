package spbuhomework.hw1.task2

fun recursiveFactorial(number: Int): Long {
    if (number <= 1) {
        return 1
    }
    return number * recursiveFactorial(number - 1)
}

fun cyclicFactorial(number: Int): Long {
    var answer = 1.toLong()
    for (i in 1..number) {
        answer *= i
    }
    return answer
}

fun main(args: Array<String>) {
    println(
        "Enter the way of operating:\n" +
                "(r - recursively\n" +
                "l - by the loop)"
    )
    val theWayOfOperating = readLine()
    println("Enter the number:")
    val number = readLine()?.toInt()
    if (number == null || number < 0) {
        println("Factorial of this number does not defined")
        return
    }
    when (theWayOfOperating) {
        "r" -> {
            val answer = recursiveFactorial(number)
            println("Answer: $answer")
        }
        "l" -> {
            println("Answer: ${cyclicFactorial(number)}")
        }
        else -> {
            println("Wrong operation")
        }
    }
}
