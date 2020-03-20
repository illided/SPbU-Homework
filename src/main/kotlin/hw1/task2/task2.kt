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

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    println(
        "Enter the way of operating:\n" +
                "(r - recursively\n" +
                "l - by the loop)"
    )
    val theWayOfOperating = scan.next()
    println("Enter the number:")
    val number = scan.nextInt()
    if (number < 0) {
        println("Factorial of $number does not defined")
        return
    }
    when (theWayOfOperating) {
        "r" -> {
            val answer = recursiveFactorial(number)
            println("Answer: $answer")
        }
        "l" -> {
            println("Answer: " + cyclicFactorial(number))
        }
        else -> {
            println("Something went wrong")
        }
    }
}
