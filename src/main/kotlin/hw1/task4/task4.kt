package spbuhomework.hw1.task4

fun isPalindrome(input: String): Boolean {
    var i = 0
    var j = input.length - 1
    while (i < j) {
        if (input[i] != input[j]) {
            return false
        }
        i++
        j--
    }
    return true
}

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    println("Enter the string: ")
    var input = scan.nextLine()
    if (isPalindrome(input)) {
        println("This string is a palindrome!")
    } else {
        println("This string is not a palindrome!")
    }
}
