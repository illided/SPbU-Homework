package spbuhomework.hw4.task1

import java.io.File
import java.io.FileNotFoundException

class HashTableManipulator() {
    var hashTable = HashTable<String, String>(initialHashFunction = ::simpleHashFunction)
    var currentInput: List<String> = listOf()

    private fun loadFromFile(inputFilePath: String, hashFunction: (String) -> Int): HashTable<String, String> {
        val hashTableFromFile = HashTable<String, String>(initialHashFunction = hashFunction)
        if (!File(inputFilePath).exists()) {
            throw FileNotFoundException("File does not exist or have different path")
        }
        var entry: List<String>
        File(inputFilePath).forEachLine {
            entry = it.split(" ")
            hashTableFromFile.put(entry[0], entry[1])
        }
        return hashTableFromFile
    }

    fun add() {
        if (hashTable.isContains(currentInput[1])) {
            println("Hashtable already have an item with this key")
        } else {
            hashTable.put(currentInput[1], currentInput[2])
            println("Item was added!")
        }
    }

    fun get() {
        if (hashTable.isContains(currentInput[1])) {
            println("Search result: " + hashTable.get(currentInput[1]))
        } else {
            println("Hashtable does not have an item with this key")
        }
    }

    fun getAll() {
        println(hashTable.toString())
    }

    fun remove() {
        if (hashTable.isContains(currentInput[1])) {
            hashTable.remove(currentInput[1])
            println("Item was removed!")
        } else {
            println("Hashtable does not have an item with this key")
        }
    }

    fun synchronizeWithFile() {
        hashTable = loadFromFile(DEFAULT_FILE_PATH, hashTable.hashFunction)
        println("${hashTable.size} entries was loaded")
    }

    fun getStatisticAboutTable() {
        println(hashTable.getStatisticString())
    }

    fun changeHashFunction() {
        if (hashTable.hashFunction == ::simpleHashFunction) {
            hashTable.hashFunction = ::polynomialHashFunction
            println("Hash function was changed. Current hash function: polynomial")
        } else {
            hashTable.hashFunction = ::simpleHashFunction
            println("Hash function was changed. Current hash function: simple")
        }
    }

    fun exit() {
        println("Goodbye!")
    }
}
