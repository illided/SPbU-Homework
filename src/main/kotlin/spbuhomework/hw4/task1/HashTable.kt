package spbuhomework.hw4.task1

import kotlin.math.abs

class HashTable<K : Comparable<K>, V> {
    private var arraySize: Int = 5
    private var conflictNumber = 0

    var extendThreshold: Int = 5
        private set(value) {
            if (value < 2) {
                throw IllegalArgumentException("Hashtable extend threshold cannot be less than 2")
            } else {
                field = value
            }
        }

    var extendFactor: Double = 2.0
        set(value) {
            if (value <= 1) {
                throw IllegalArgumentException("Hashtable extend factor cannot be less or equal 1")
            } else {
                field = value
            }
        }

    var size = 0
        private set

    private var arrayOfList: Array<MutableList<Entry<K, V>>> = Array(arraySize) { mutableListOf<Entry<K, V>>() }

    var hashFunction: (K) -> Int = { key: K -> key.hashCode() }
        set(value) {
            field = value
            refill()
        }

    val loadFactor: Double
        get() = size / ((arraySize * extendThreshold).toDouble())

    private fun getHash(key: K): Int {
        return abs(hashFunction(key)) % arraySize
    }

    private fun refill(newArraySize: Int = arraySize, newExtendThreshold: Int = extendThreshold) {
        val newArrayOfList: Array<MutableList<Entry<K, V>>> = Array(newArraySize) { mutableListOf<Entry<K, V>>() }
        conflictNumber = 0
        var targetList: MutableList<Entry<K, V>>
        for (listOfEntry in arrayOfList) {
            for (entry in listOfEntry) {
                targetList = newArrayOfList[getHash(entry.key)]
                if (targetList.isNotEmpty()) {
                    conflictNumber++
                }
                targetList.add(entry)
            }
        }
        arraySize = newArraySize
        extendThreshold = newExtendThreshold
        arrayOfList = newArrayOfList
    }

    private fun find(key: K): Entry<K, V>? {
        return arrayOfList[getHash(key)].find { it.key == key }
    }

    fun get(key: K): V? {
        if (isContains(key)) {
            return find(key)?.value
        } else {
            throw IndexOutOfBoundsException("Don't have an item with this key in hashtable")
        }
    }

    fun isContains(key: K): Boolean {
        return find(key) != null
    }

    fun put(key: K, value: V) {
        if (!isContains(key)) {
            val targetList = arrayOfList[getHash(key)]
            if (targetList.isNotEmpty()) {
                conflictNumber++
            }
            targetList.add(Entry(key, value))
            if (targetList.size > extendThreshold) {
                refill((extendFactor * arraySize).toInt(), (extendFactor * extendThreshold).toInt())
            }
            size++
        }
    }

    fun remove(key: K) {
        if (isContains(key)) {
            arrayOfList[getHash(key)].remove(find(key))
            size--
        } else {
            throw IndexOutOfBoundsException("Don't have an item with this key in hashtable")
        }
    }

    fun getStatisticString(): String {
        var maximumListLength: Int = 0
        for (listOfEntry in arrayOfList) {
            maximumListLength = maxOf(maximumListLength, listOfEntry.size)
        }
        return "Current size: $size\n" +
                "Potentially maximum number of cells: ${arraySize * extendThreshold}\n" +
                "Current load factor: $loadFactor\n" +
                "Number of conflicts since last refill: $conflictNumber\n" +
                "Maximum length of conflict list: $maximumListLength"
    }

    fun getStructuralString(): String {
        var outputString = ""
        for (i in arrayOfList.indices) {
            outputString += "$i -> "
            for (entry in arrayOfList[i]) {
                outputString += "$entry"
            }
            outputString += "\n"
        }
        return outputString
    }
}
