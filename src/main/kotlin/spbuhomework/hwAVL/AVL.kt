package spbuhomework.hwAVL

import kotlin.IndexOutOfBoundsException

class AVL<K : Comparable<K>, V> : MutableMap<K, V> {

    internal class Node<K, V>(
        val key: K,
        val value: V,
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
    }

    private fun walkToSpecificNode(startingNode: Node<K, V>, key: K, stopFun: (Node<K, V>) -> Boolean): Node<K, V> {
        var currentNode: Node<K, V> = startingNode
        while (!stopFun(currentNode)) {
            val leftChild = currentNode.leftChild
            val rightChild = currentNode.rightChild
            currentNode = if (key < currentNode.key && leftChild != null) {
                leftChild
            } else if (key > currentNode.key && rightChild != null) {
                rightChild
            } else {
                throw IndexOutOfBoundsException("This node does not exist")
            }
        }
        return currentNode
    }

    private fun <T> walkEverywhereAndDo(
        currentNode: Node<K, V>,
        action: (Node<K, V>, T?) -> Unit,
        additional: T? = null
    ) {
        val leftChild = currentNode.leftChild
        if (leftChild != null) {
            walkEverywhereAndDo(leftChild, action, additional)
        }
        action(currentNode, additional)
        val rightChild = currentNode.rightChild
        if (rightChild != null) {
            walkEverywhereAndDo(rightChild, action, additional)
        }
    }

    private var root: Node<K, V>? = null

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun containsKey(key: K): Boolean {
        val currentRoot = root ?: return false
        return try {
            val node = walkToSpecificNode(currentRoot, key, { it.key == key })
            true
        } catch (exception: IndexOutOfBoundsException) {
            false
        }
    }

    override fun containsValue(value: V): Boolean {
        TODO("Not yet implemented")
    }

    override fun get(key: K): V? {
        val currentRoot = root ?: return null
        return try {
            val node = walkToSpecificNode(currentRoot, key, { it.key == key })
            node.value
        } catch (exception: IndexOutOfBoundsException) {
            null
        }
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override val entries: MutableSet<MutableMap.MutableEntry<K, V>>
        get() = TODO()
    override val keys: MutableSet<K>
        get() = TODO("Not yet implemented")
    override val values: MutableCollection<V>
        get() = TODO("Not yet implemented")

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun put(key: K, value: V): V? {
        val currentRoot = root
        if (currentRoot == null) {
            root = Node(key, value)
            return value
        }
        try {
            val newParent = walkToSpecificNode(
                currentRoot,
                key,
                { (key < it.key && it.leftChild == null) || (it.key < key && it.rightChild == null) })
            val newNode = Node(key, value, newParent)
            if (key < newParent.key && newParent.leftChild == null) {
                newParent.leftChild = newNode
            } else if (key > newParent.key && newParent.rightChild == null) {
                newParent.rightChild = newNode
            }
        } catch (exception: IndexOutOfBoundsException) {
            return null
        }
        return value
    }

    override fun putAll(from: Map<out K, V>) {
        TODO("Not yet implemented")
    }

    override fun remove(key: K): V? {
        return null
    }

    override fun toString(): String {
        TODO()
    }

    fun print() {
        val currentRoot = root
        print('{')
        if (currentRoot == null) {
            print('}')
            return
        }
        walkEverywhereAndDo(
            currentRoot, { node: Node<K, V>, _: String? -> print(" ( ${node.key} : ${node.value} )") })
        print(" }")
    }

}