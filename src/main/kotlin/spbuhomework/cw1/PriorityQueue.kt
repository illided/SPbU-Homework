package spbuhomework.cw1

import java.lang.IndexOutOfBoundsException

class PriorityQueue<T : Comparable<T>> {

    inner class Entry(val content: T, var priority: Int)

    private val entrySet: MutableList<Entry> = mutableListOf()

    fun enqueue(content: T, priority: Int) {
        if (entrySet.find { it.content == content } == null) {
            entrySet.add(Entry(content, priority))
        }
    }

    fun dequeue(): T {
        val topPriorityElement: Entry = entrySet.maxBy { it.priority }
            ?: throw IndexOutOfBoundsException("Tried to dequeue element from empty priority queue")
        entrySet.remove(topPriorityElement)
        return topPriorityElement.content
    }

}