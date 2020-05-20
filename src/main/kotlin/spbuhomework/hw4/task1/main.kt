package spbuhomework.hw4.task1

import kotlin.math.pow

fun loadFromFile() {

}

fun simpleHashFunction(input: String) : Int {
    var hash = 0
    for (symbol in input) {
        hash += symbol.toInt()
    }
    return hash
}

fun polynomialHashFunction(input: String) : Int {
    var hash = 0
    var index = 0
    for (symbol in input) {
        hash += (symbol.toInt() * (5.0).pow(index)).toInt()
        index++
    }
    return hash
}

fun main() {
    val myHashTable = HashTable<String, String>(initialHashFunction = ::simpleHashFunction)


}