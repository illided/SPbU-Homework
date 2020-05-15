package spbuhomework.hw6

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.runBlocking
import kotlin.system.measureTimeMillis

fun <T : Comparable<T>> basicQuicksort(items: List<T>): List<T> {
    if (items.count() < 1) return items
    val pivot = items[items.count() / 2]
    val equal = items.filter { it == pivot }
    val less = items.filter { it < pivot }
    val greater = items.filter { it > pivot }
    return basicQuicksort(less) + equal + basicQuicksort(
        greater
    )
}

suspend fun <T : Comparable<T>> realQuicksort(items: List<T>): List<T> {
    if (items.count() < 1) return items
    val pivot = items[items.count() / 2]
    val equal = items.filter { it == pivot }
    val less = async { realQuicksort(items.filter { it < pivot }) }
    val greater = async { realQuicksort(items.filter { it > pivot }) }
    return less.await() + equal + greater.await()
}

fun createPsuedoRandomList(seed: Int = 5) : List<Int> {
    val myList = mutableListOf<Int>()
    for (i in 1..seed) {
        myList.add(seed * i)
        myList.add(seed * i - seed)
        myList.add(seed - i)
    }
    return myList
}

fun main() = runBlocking<Unit> {
    val myList = createPsuedoRandomList()

    val time = measureTimeMillis { val mySortedList = basicQuicksort(myList) }
    println("Standart quick sort: $time")

    val newTime = measureTimeMillis { val mySortedList = realQuicksort(myList) }
    println("Fast quick sort: $newTime")
}
