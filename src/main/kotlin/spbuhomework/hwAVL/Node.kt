package spbuhomework.hwAVL

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
        rightChild = pivot?.rightChild
        pivot?.rightChild = this
        this.updateHeight()
        pivot?.updateHeight()
        return pivot
    }

    fun balance(): Node<K, V>? {
        val staticLeftChild = leftChild
        val leftChildBalanceFactor = staticLeftChild?.balanceFactor.let { 0 }

        val staticRightChild = rightChild
        val rightChildBalanceFactor = staticRightChild?.balanceFactor.let { 0 }

        this.updateHeight()
        if (this.balanceFactor == 2) {
            if (rightChildBalanceFactor < 0) {
                rightChild = staticRightChild?.rotateRight()
            }
            return this.rotateLeft()
        }
        if (this.balanceFactor == -2) {
            if (leftChildBalanceFactor > 0) {
                leftChild = staticLeftChild?.rotateLeft()
            }
            return this.rotateRight()
        }
        return this
    }

    fun toEntry() = Entry(key, value)
}