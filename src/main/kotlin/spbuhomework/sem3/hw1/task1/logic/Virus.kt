package spbuhomework.sem3.hw1.task1.logic

open class Virus(
    val infectivity: Double,
    val symptomatic: Double,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
