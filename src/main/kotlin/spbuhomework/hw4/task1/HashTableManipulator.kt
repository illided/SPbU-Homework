package spbuhomework.hw4.task1

import java.io.FileNotFoundException
import kotlin.math.pow

class HashTableManipulator(inputFilePath: String) {
    private var hashTable = HashTable<String, String>(initialHasher = SimpleHasher())
    var currentInput: List<String> = listOf()
    private var inputFileContent: List<String> =
        {}.javaClass.classLoader.getResource(inputFilePath)?.readText()?.split("\n")
            ?: throw FileNotFoundException("File does not exist or have different path")

    private fun loadFromFile(): HashTable<String, String> {
        val hashTableFromFile = HashTable<String, String>(initialHasher = hashTable.currentHasher)
        var entry: List<String>
        for (line in inputFileContent) {
            entry = line.split(" ")
            require(entry.size == 2) { "Illegal number of arguments in file line" }
            hashTableFromFile.put(entry[0], entry[1])
        }
        return hashTableFromFile
    }

    fun add() {
        if (hashTable.put(currentInput[1], currentInput[2])) {
            println("Item was added!")
        } else {
            println("Hashtable already have an item with this key")
        }
    }

    fun get() {
        try {
            println("Search result: " + hashTable.get(currentInput[1]))
        } catch (exception: IllegalArgumentException) {
            println("Hashtable does not have an item with this key")
        }
    }

    fun getAll() = println(hashTable.toString())

    fun remove() {
        try {
            hashTable.remove(currentInput[1])
            println("Item was removed!")
        } catch (exception: IllegalArgumentException) {
            println("Hashtable does not have an item with this key")
        }
    }

    fun synchronizeWithFile() {
        hashTable = loadFromFile()
        println("${hashTable.size} entries was loaded")
    }

    fun getStatisticAboutTable() = println(hashTable.getStatisticString())

    fun changeHashFunction() {
        if (hashTable.currentHasher is SimpleHasher) {
            hashTable.currentHasher = PolynomialHasher()
            println("Hash function was changed. Current hash function: polynomial")
        } else {
            hashTable.currentHasher = SimpleHasher()
            println("Hash function was changed. Current hash function: simple")
        }
    }

    fun exit() = println("Goodbye!")
}

class SimpleHasher : Hasher<String> {
    override fun hashFunction(input: String): Int = input.map { it.toInt() }.sum()
}

class PolynomialHasher : Hasher<String> {
    companion object {
        private const val PRIME_NUMBER = 5.0
    }

    override fun hashFunction(input: String): Int =
        input.mapIndexed { index, c -> c.toInt() * (PRIME_NUMBER).pow(index) }.sum().toInt()
}
