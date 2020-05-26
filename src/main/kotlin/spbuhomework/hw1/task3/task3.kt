package spbuhomework.hw1.task3

fun getNumberOfInclusions(mainString: String, subString: String): Int {
    if (subString.isEmpty() && mainString.isNotEmpty()) {
        return 0
    }
    var inclusions = 0
    for (i in 0..mainString.length - subString.length) {
        if (mainString.slice(i until i + subString.length) == subString) {
            inclusions++
        }
    }
    return inclusions
}

fun main(args: Array<String>) {
    println("Enter the main string:")
    val mainString = readLine()
    println("Enter the substring:")
    val subString = readLine()
    if (mainString == null || subString == null) {
        println("Wrong input")
    } else {
        println("Answer: ${getNumberOfInclusions(mainString, subString)}")
    }
}
