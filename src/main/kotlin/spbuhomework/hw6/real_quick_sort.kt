package spbuhomework.hw6

import kotlin.random.Random
import kotlin.random.nextInt

import kotlin.system.measureTimeMillis

fun createRandomArray(lowestValue: Int = 1, highestValue: Int = 100, arrayLength: Int = 50000): Array<Int> {
    val newArray = Array(arrayLength) { 0 }
    for (i in 0 until arrayLength) {
        newArray[i] = Random.nextInt(lowestValue..highestValue)
    }
    return newArray
}

fun main() {
    val myArray = createRandomArray()
    val standardQuickSort = StandardQuickSort()
    val asyncQuickSort = AsyncQuickSort()

    val basicSortTime = measureTimeMillis { standardQuickSort.sort(myArray) }
    println("Standard quick sort: $basicSortTime")

    val asyncSortTime = measureTimeMillis { asyncQuickSort.sort(myArray) }
    println("Fast quick sort: $asyncSortTime")

    val sortNameArray = arrayOf("Standard quick sort ", " async quick sort")
    when {
        basicSortTime < asyncSortTime -> println(sortNameArray.joinToString("faster than"))
        basicSortTime > asyncSortTime -> println(sortNameArray.joinToString("slower than"))
        else -> println(sortNameArray.joinToString("have same performance as"))
    }
}
