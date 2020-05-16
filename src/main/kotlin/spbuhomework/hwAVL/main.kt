package spbuhomework.hwAVL

fun main() {
    val myAVL = AVL<Int, Int>()
    myAVL[1] = 2
    myAVL[2] = 5
    myAVL[4] = 7
    myAVL.print()
}