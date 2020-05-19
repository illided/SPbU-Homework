package spbuhomework.hw4.task1

import kotlin.math.abs

class HashTable<K : Comparable<K>, V>(
    var arraySize: Int = 5,
    var extendThreshold: Int = 11,
    var extendFactor: Int = 2
) {
    var size = 0
    private set
    private var arrayOfList: Array<MutableList<Entry<K, V>>> = Array(arraySize) { mutableListOf<Entry<K, V>>() }

    var hashFunction: (K) -> Int = { key: K -> key.hashCode() }
        set(value) {
            field = value
            extend(arraySize)
        }

    private fun getHash(key: K): Int {
        return abs(hashFunction(key)) % arraySize
    }

    private fun extend(newSize: Int) {
        val newArrayOfList: Array<MutableList<Entry<K, V>>> = Array(newSize) { mutableListOf<Entry<K, V>>() }
        for (listOfEntry in arrayOfList) {
            for (entry in listOfEntry) {
                putInArrayOfList(newArrayOfList, entry.key, entry.value)
            }
        }
        arraySize = newSize
        arrayOfList = newArrayOfList
    }

    private fun find(key: K): Entry<K, V>? {
        return arrayOfList[getHash(key)].find { it.key == key }
    }

    fun get(key: K): V?{
        if (isContains(key)){
            return find(key)?.value
        } else {
            throw IndexOutOfBoundsException("Don't have an item with this key in hashtable")
        }
    }

    fun isContains(key: K): Boolean {
        return find(key) != null
    }

    private fun putInArrayOfList(arrayOfList: Array<MutableList<Entry<K, V>>>, key: K, value: V) {
        val targetList = arrayOfList[getHash(key)]
        targetList.add(Entry(key, value))
        if (targetList.size >= extendThreshold) {
            extend(extendFactor * arraySize)
        }
    }

    fun put(key: K, value: V) {
        if (!isContains(key)) {
            putInArrayOfList(arrayOfList, key, value)
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

    fun getStatisticString() {

    }

}