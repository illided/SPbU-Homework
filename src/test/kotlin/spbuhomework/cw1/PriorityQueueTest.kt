package spbuhomework.cw1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*


internal class PriorityQueueTest {

    @Test
    fun PriorityQueue_oneNumberInput_sameNumberOutput() {
        val queue = PriorityQueue<Int>()
        queue.enqueue(5, 1)
        assertEquals(queue.dequeue(), 5)
    }

    @Test
    fun PriorityQueue_oneStringInput_sameStringOutput() {
        val queue = PriorityQueue<String>()
        queue.enqueue("Putin", 200)
        assertEquals(queue.dequeue(), "Putin")
    }

    @Test
    fun PriorityQueue_multipleNumbersInput_correctOutput() {
        val queue = PriorityQueue<Int>()
        for (i in 1..100) {
            queue.enqueue(i, i)
        }
        for (i in 100 downTo 1) {
            assertEquals(i, queue.dequeue())
        }
    }

    @Test
    fun PriorityQueue_multipleStringInput_correctOutput() {
        val queue = PriorityQueue<String>()
        val cities = listOf("Amsterdam", "Moscow", "New-York", "Kiev")
        val priorities = listOf(2, 100, 3, 50)

        for ((i, city) in cities.withIndex()) {
            queue.enqueue(city, priorities[i])
        }

        val citiesOrganized = listOf("Moscow", "Kiev", "New-York", "Amsterdam")
        for (i in 0..3) {
            assertEquals(citiesOrganized[i], queue.dequeue())
        }
    }
}