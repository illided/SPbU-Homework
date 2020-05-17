package spbuhomework.hwAVL

fun main() {
    val myAVL = AVL<Int, Int>()
    myAVL.put(1, 6)
    myAVL.put(2, 5)
    myAVL.put(4, 7)
    println(myAVL.toString())
    myAVL.remove(4)
    println(myAVL.toString())
    myAVL.remove(1)
    println(myAVL.toString())
    myAVL.remove(2)
    println(myAVL.toString())
}