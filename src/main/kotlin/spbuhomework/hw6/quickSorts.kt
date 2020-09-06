package spbuhomework.hw6

import kotlinx.coroutines.experimental.runBlocking

abstract class Sort {
    abstract fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T>

    fun <T> partition(inputArray: Array<T>, left: Int, right: Int): Int where T : Comparable<T> {
        var i = left
        var j = right
        val middle = inputArray[(left + right) / 2]
        while (i <= j) {
            while (inputArray[i] < middle) {
                i++
            }
            while (inputArray[j] > middle) {
                j--
            }
            if (i >= j) {
                break
            }
            inputArray[i] = inputArray[j].also { inputArray[j] = inputArray[i] }
            i++
            j--
        }
        return j
    }
}

class StandardQuickSort : Sort() {
    override fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T> {
        runStandardQuickSort(inputArray, 0, inputArray.size - 1)
        return inputArray
    }

    private fun <T> runStandardQuickSort(inputArray: Array<T>, left: Int, right: Int) where T : Comparable<T> {
        if (left < right) {
            val middleIndex = partition(inputArray, left, right)
            runStandardQuickSort(inputArray, left, middleIndex)
            runStandardQuickSort(inputArray, middleIndex + 1, right)
        }
    }
}

class AsyncQuickSort : Sort() {
    override fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T> {
        runBlocking { runAsyncQuickSort(inputArray, 0, inputArray.size - 1) }
        return inputArray
    }

    private suspend fun <T> runAsyncQuickSort(inputArray: Array<T>, left: Int, right: Int) where T : Comparable<T> {
        if (left < right) {
            val middleIndex = partition(inputArray, left, right)
            runAsyncQuickSort(inputArray, left, middleIndex)
            runAsyncQuickSort(inputArray, middleIndex + 1, right)
        }
    }
}
