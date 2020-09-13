package spbuhomework.sem3.hw1.task1.logic

open class Virus(
    val infectivity: Double,
    val symptomatic: Double,
    val name: String
) {
    init {
        require(infectivity in 0.0..1.0) {"Invalid infectivity (must be between 0 and 1"}
        require(symptomatic in 0.0..1.0) {"Invalid symptomatic (must be between 0 ans 1"}
    }
    override fun toString(): String {
        return name
    }
}
