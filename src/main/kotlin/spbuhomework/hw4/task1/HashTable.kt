package spbuhomework.hw4.task1

import kotlin.math.abs

const val DEFAULT_ARRAY_SIZE = 5
const val DEFAULT_EXTEND_FACTOR = 2.0
const val DEFAULT_EXTEND_THRESHOLD = 5

class HashTable<K : Comparable<K>, V>(
    initialExtendThreshold: Int = DEFAULT_EXTEND_THRESHOLD,
    initialArraySize: Int = DEFAULT_ARRAY_SIZE,
    initialExtendFactor: Double = DEFAULT_EXTEND_FACTOR,
    initialHashFunction: (K) -> Int = { it.hashCode() }
) {
    private var arraySize: Int = DEFAULT_ARRAY_SIZE
        private set(value) {
            require(value >= 1) { "Hashtable array size cannot be less than 1" }
            field = value
        }

    private var arrayOfList: Array<MutableList<Entry<K, V>>> = Array(arraySize) { mutableListOf<Entry<K, V>>() }

    var extendThreshold: Int = DEFAULT_EXTEND_THRESHOLD
        private set(value) {
            require(value >= 1) { "Hashtable extend threshold cannot be less than 1" }
            field = value
        }
    var extendFactor: Double = DEFAULT_EXTEND_FACTOR
        set(value) {
            require(value >= 1) { "Hashtable extend factor cannot be less than 1" }
            field = value
        }
    var hashFunction: (K) -> Int = { key: K -> key.hashCode() }
        set(value) {
            field = value
            refill()
        }

    init {
        extendThreshold = initialExtendThreshold
        arraySize = initialArraySize
        extendFactor = initialExtendFactor
        hashFunction = initialHashFunction
    }

    private var conflictNumber = 0

    var size = 0
        private set

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
        var maximumListLength = 0
        for (listOfEntry in arrayOfList) {
            maximumListLength = maxOf(maximumListLength, listOfEntry.size)
        }
        return "Current size: $size\n" +
                "Potentially maximum number of cells: ${arraySize * extendThreshold}\n" +
                "Current load factor: $loadFactor\n" +
                "Number of conflicts since last refill: $conflictNumber\n" +
                "Maximum length of conflict list: $maximumListLength"
    }

    override fun toString(): String {
        var outputString = "{"
        for (listOfEntry in arrayOfList) {
            for (entry in listOfEntry) {
                outputString += entry.toString()
            }
        }
        return "$outputString}"
    }
}
