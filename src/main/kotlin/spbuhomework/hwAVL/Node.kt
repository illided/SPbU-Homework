package spbuhomework.hwAVL

import kotlin.math.abs

class Node<K : Comparable<K>, V>(
    var key: K,
    var value: V,
    var leftChild: Node<K, V>? = null,
    var rightChild: Node<K, V>? = null
) {
    var height: Int = 0

    private fun updateHeight() {
        val leftChildHeight = leftChild?.height ?: 0
        val rightChildHeight = rightChild?.height ?: 0
        height = maxOf(leftChildHeight, rightChildHeight) + 1
    }

    private val balanceFactor: Int
        get() {
            val leftChildHeight = leftChild?.height ?: 0
            val rightChildHeight = rightChild?.height ?: 0
            return rightChildHeight - leftChildHeight
        }

    private fun rotateLeft(): Node<K, V>? {
        val pivot = rightChild
        rightChild = pivot?.leftChild
        pivot?.leftChild = this
        updateHeight()
        pivot?.updateHeight()
        return pivot
    }

    private fun rotateRight(): Node<K, V>? {
        val pivot = leftChild
        leftChild = pivot?.rightChild
        pivot?.rightChild = this
        this.updateHeight()
        pivot?.updateHeight()
        return pivot
    }

    fun balance(balanceThreshold: Int = 2): Node<K, V>? {
        val staticLeftChild = leftChild
        val leftChildBalanceFactor = staticLeftChild?.balanceFactor.let { 0 }

        val staticRightChild = rightChild
        val rightChildBalanceFactor = staticRightChild?.balanceFactor.let { 0 }

        var outputNode: Node<K, V>? = null

        this.updateHeight()
        val staticBalanceFactor = balanceFactor
        if (abs(staticBalanceFactor) == balanceThreshold) {
            if (staticBalanceFactor == balanceThreshold) {
                if (rightChildBalanceFactor < 0) {
                    rightChild = staticRightChild?.rotateRight()
                }
                outputNode = this.rotateLeft()
            } else if (staticBalanceFactor == -1 * balanceThreshold) {
                if (leftChildBalanceFactor > 0) {
                    leftChild = staticLeftChild?.rotateLeft()
                }
                outputNode = this.rotateRight()
            }
            return outputNode
        }
        outputNode = this
        return outputNode
    }

    fun toEntry() = Entry(key, value)
}
