package spbuhomework.hw1.task4

fun isPalindrome(input: String): Boolean {
    val charDropNum = input.length / 2 + input.length % 2
    return input.dropLast(charDropNum) == input.drop(charDropNum).reversed()
}

fun main() {
    println("Enter the string: ")
    val input = readLine()
    when {
        input == null -> println("Wrong input")
        isPalindrome(input) -> println("This string is a palindrome!")
        else -> println("This string is not a palindrome!")
    }
}
