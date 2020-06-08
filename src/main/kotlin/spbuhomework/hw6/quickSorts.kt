package spbuhomework.hw6

import kotlinx.coroutines.experimental.runBlocking

interface Sort {
    fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T>
}

class StandardQuickSort : Sort {
    override fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T> {
        runStandardQuickSort(inputArray, 0, inputArray.size - 1)
        return inputArray
    }

    private fun <T> runStandardQuickSort(inputArray: Array<T>, left: Int, right: Int) where T : Comparable<T> {
        if (left < right) {
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
            runStandardQuickSort(inputArray, left, j)
            runStandardQuickSort(inputArray, j + 1, right)
        }
    }
}

class AsyncQuickSort : Sort {
    override fun <T> sortArray(inputArray: Array<T>): Array<T> where T : Comparable<T> {
        runBlocking { runAsyncQuickSort(inputArray, 0, inputArray.size - 1) }
        return inputArray
    }

    private suspend fun <T> runAsyncQuickSort(inputArray: Array<T>, left: Int, right: Int) where T : Comparable<T> {
        if (left < right) {
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
            runAsyncQuickSort(inputArray, left, j)
            runAsyncQuickSort(inputArray, j + 1, right)
        }
    }
}
