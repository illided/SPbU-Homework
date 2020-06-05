package spbuhomework.hw4.task1

interface Hasher<T> {
    fun hashFunction(input: T): Int
}
