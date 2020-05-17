package spbuhomework.hwAVL

class Node<K : Comparable<K>, V>(
    var key: K,
    var value: V,
    var parent: Node<K, V>? = null,
    var leftChild: Node<K, V>? = null,
    var rightChild: Node<K, V>? = null
) {
    var height: Int = 0

    fun updateHeight() {
        val leftChildHeight = leftChild?.height.let { 0 }
        val rightChildHeight = rightChild?.height.let { 0 }
        height = maxOf(leftChildHeight, rightChildHeight)
    }

    fun isLeaf(): Boolean {
        return (leftChild == null && rightChild == null)
    }

    fun delete() {
        val currentParent = parent ?: return
        when {
            currentParent.leftChild?.key == key -> {
                currentParent.leftChild = null
            }
            currentParent.rightChild?.key == key -> {
                currentParent.rightChild = null
            }
        }
    }

    fun toEntry() = Entry(key, value)
}