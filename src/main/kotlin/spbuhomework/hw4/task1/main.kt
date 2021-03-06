package spbuhomework.hw4.task1

const val DEFAULT_FILE_PATH = "spbuhomework/hw4/task1/"
const val GREETINGS = "Hello! I am a HashTable. Here are some functions you can do:\n" +
        "add <key> <value> - Add entry to the hashtable\n" +
        "get <key> - Search for entry with the specific key\n" +
        "getAll - Get all the entries that are in the hashtable\n" +
        "remove <key> - Remove entry with specific key\n" +
        "sync - Synchronize your hashtable with the file\n" +
        "getStat - Get statistics about the current state of the hashtable\n" +
        "switch - Switch to the other hash function (there are simple and polynomial)\n" +
        "exit - exit the interactive mode"

object WordNum {
    const val THREE_WORD = 3
    const val TWO_WORD = 2
    const val ONE_WORD = 1
}

enum class Command(val index: String, val length: Int) {
    Add("add", WordNum.THREE_WORD),
    Get("get", WordNum.TWO_WORD),
    GetAll("getAll", WordNum.ONE_WORD),
    Remove("remove", WordNum.TWO_WORD),
    SynchronizeWithFile("sync", WordNum.ONE_WORD),
    GetStatisticAboutTable("getStat", WordNum.ONE_WORD),
    ChangeHashFunction("switch", WordNum.ONE_WORD),
    Exit("exit", WordNum.ONE_WORD);
}

fun findCommand(commandAsList: List<String>?): Command? {
    if (commandAsList == null) {
        return null
    }
    return Command.values().find { it.length == commandAsList.size && it.index == commandAsList[0] }
}

fun main() {
    println(GREETINGS)
    var currentInput: List<String>?
    var currentCommand: Command?
    val manipulator = HashTableManipulator(DEFAULT_FILE_PATH + "text.txt")
    do {
        currentInput = readLine()?.split(" ")
        currentCommand = findCommand(currentInput)
        if (currentCommand != null && currentInput != null) {
            manipulator.currentInput = currentInput
            when (currentCommand) {
                null -> println("Wrong command input")
                Command.Add -> manipulator.add()
                Command.Get -> manipulator.get()
                Command.GetAll -> manipulator.getAll()
                Command.Remove -> manipulator.remove()
                Command.SynchronizeWithFile -> manipulator.synchronizeWithFile()
                Command.GetStatisticAboutTable -> manipulator.getStatisticAboutTable()
                Command.ChangeHashFunction -> manipulator.changeHashFunction()
                Command.Exit -> manipulator.exit()
            }
        } else {
            println("Wrong command")
        }
    } while (currentCommand != Command.Exit)
}
