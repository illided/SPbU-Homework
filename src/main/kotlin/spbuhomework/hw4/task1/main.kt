package spbuhomework.hw4.task1

import java.io.File
import java.io.FileNotFoundException
import kotlin.math.pow

const val DEFAULT_FILE_PATH = "./src/main/kotlin/spbuhomework/hw4/task1/text.txt"
const val GREETINGS = "Hello! I am a HashTable. Here are some functions you can do:\n" +
        "add <key> <value> - Add entry to the hashtable\n" +
        "get <key> - Search for entry with the specific key\n" +
        "getAll - Get all the entries that are in the hashtable\n" +
        "remove <key> - Remove entry with specific key\n" +
        "sync - Synchronize your hashtable with the file\n" +
        "getStat - Get statistics about the current state of the hashtable\n" +
        "switch - Switch to the other hashfunction (there are simple and polynomial)\n" +
        "exit - exit the interactive mode"

fun loadFromFile(inputFilePath: String, hashFunction: (String) -> Int): HashTable<String, String> {
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

fun simpleHashFunction(input: String): Int {
    var hash = 0
    for (symbol in input) {
        hash += symbol.toInt()
    }
    return hash
}

fun polynomialHashFunction(input: String): Int {
    var hash = 0
    var index = 0
    for (symbol in input) {
        hash += (symbol.toInt() * (5.0).pow(index)).toInt()
        index++
    }
    return hash
}

fun main() {
    var myHashTable = HashTable<String, String>(initialHashFunction = ::simpleHashFunction)
    println(GREETINGS)
    var currentCommand = readLine()?.split(" ")
    while (currentCommand?.get(0) != "exit") {
        when {
            currentCommand == null || currentCommand.isEmpty() -> println("Wrong command input")

            currentCommand[0] == "add" && currentCommand.size == 3 -> {
                if (myHashTable.isContains(currentCommand[1])) {
                    println("Hashtable already have an item with this key")
                } else {
                    myHashTable.put(currentCommand[1], currentCommand[2])
                    println("Item was added!")
                }
            }

            currentCommand[0] == "get" && currentCommand.size == 2 -> {
                if (myHashTable.isContains(currentCommand[1])) {
                    println("Search result: " + myHashTable.get(currentCommand[1]))
                } else {
                    println("Hashtable does not have an item with this key")
                }
            }

            currentCommand[0] == "getAll" && currentCommand.size == 1 -> {
                println(myHashTable.toString())
            }

            currentCommand[0] == "remove" && currentCommand.size == 2 -> {
                if (myHashTable.isContains(currentCommand[1])) {
                    myHashTable.remove(currentCommand[1])
                    println("Item was removed!")
                } else {
                    println("Hashtable does not have an item with this key")
                }
            }

            currentCommand[0] == "sync" && currentCommand.size == 1 -> {
                myHashTable = loadFromFile(DEFAULT_FILE_PATH, myHashTable.hashFunction)
                println("${myHashTable.size} entries was loaded")
            }

            currentCommand[0] == "getStat" && currentCommand.size == 1 -> {
                println(myHashTable.getStatisticString())
            }

            currentCommand[0] == "switch" && currentCommand.size == 1 -> {
                if (myHashTable.hashFunction == ::simpleHashFunction) {
                    myHashTable.hashFunction = ::polynomialHashFunction
                    println("Hash function was changed. Current hash function: polynomial")
                } else {
                    myHashTable.hashFunction = ::simpleHashFunction
                    println("Hash function was changed. Current hash function: simple")
                }
            }

            else -> println("Wrong command input")
        }
        currentCommand = readLine()?.split(" ")
    }
}