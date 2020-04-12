package spbuhomework.hw1.task3

fun getNumberOfInclusions(mainString: String, subString: String): Int {
    if (subString.isEmpty() && mainString.isNotEmpty()) {
        return 0
    }
    var inclusions = 0
    var isContaining = true

    for (i in 0 until mainString.length - subString.length + 1) {
        isContaining = true
        for (j in subString.indices) {
            if (subString[j] != mainString[i + j]) {
                isContaining = false
            }
        }
        if (isContaining) {
            inclusions++
        }
    }
    return inclusions
}

val scan = java.util.Scanner(System.`in`)
fun main(args: Array<String>) {
    println("Enter the main string:")
    val mainString = scan.nextLine()
    println("Enter the substring:")
    val subString = scan.nextLine()

    val answer = getNumberOfInclusions(mainString, subString)
    print("Answer: $answer")
}
