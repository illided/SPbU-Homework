package spbuhomework.cw2

import kotlin.math.sqrt

class CoordinateDistanceFromStartComparator : Comparator<Pair<Int, Int>> {
    override fun compare(p0: Pair<Int, Int>, p1: Pair<Int, Int>): Int {
        val distanceFromStartP0 = sqrt((p0.first * p0.first + p0.second * p0.second).toDouble())
        val distanceFromStartP1 = sqrt((p1.first * p1.first + p1.second * p1.second).toDouble())
        return when {
            distanceFromStartP0 > distanceFromStartP1 -> 1
            distanceFromStartP0 < distanceFromStartP1 -> -1
            else -> 0
        }
    }
}
