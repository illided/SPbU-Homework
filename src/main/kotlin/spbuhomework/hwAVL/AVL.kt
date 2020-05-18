package spbuhomework.hwAVL

import kotlin.IndexOutOfBoundsException

open class AVL<K : Comparable<K>, V> : Map<K, V> {

    private var root: Node<K, V>? = null
    private val walk = Walker()
    private val manipulator = Manipulator()

    inner class Walker {

        fun toSpecificNode(currentNode: Node<K, V>, key: K, stopFun: (Node<K, V>) -> Boolean): Node<K, V>? {
            val leftChild = currentNode.leftChild
            val rightChild = currentNode.rightChild
            return if (stopFun(currentNode)) {
                currentNode
            } else if (key > currentNode.key && rightChild != null) {
                toSpecificNode(rightChild, key, stopFun)
            } else if (key < currentNode.key && leftChild != null) {
                toSpecificNode(leftChild, key, stopFun)
            } else {
                null
            }
        }

        fun toSpecificNodeFromRoot(key: K, stopFun: (Node<K, V>) -> Boolean): Node<K, V>? {
            val startingNode = root ?: throw IndexOutOfBoundsException("Trying to find an item from an empty set")
            return toSpecificNode(startingNode, key, stopFun)
        }

        fun <T> everywhereAndDo(
            currentNode: Node<K, V>,
            action: (Node<K, V>, T?) -> Unit,
            additional: T? = null
        ) {
            val leftChild = currentNode.leftChild
            if (leftChild != null) {
                everywhereAndDo(leftChild, action, additional)
            }
            action(currentNode, additional)
            val rightChild = currentNode.rightChild
            if (rightChild != null) {
                everywhereAndDo(rightChild, action, additional)
            }
        }
    }

    inner class Manipulator() {  //add function put and remove

        private fun removeMin(node: Node<K, V>): Node<K, V>? {
            val leftChild = node.leftChild ?: return node.rightChild
            node.leftChild = removeMin(leftChild)
            return node.balance()
        }

        fun removeNode(node: Node<K, V>?, key: K): Node<K, V>? {
            if (node == null) {
                return null
            }
            when {
                (key < node.key) -> node.leftChild = removeNode(node.leftChild, key)
                (key > node.key) -> node.rightChild = removeNode(node.rightChild, key)
                else -> {
                    val leftChild = node.leftChild
                    val rightChild = node.rightChild ?: return leftChild
                    val localMin = walk.toSpecificNode(rightChild, key, { it.leftChild == null })
                    localMin?.rightChild = removeMin(rightChild)
                    localMin?.leftChild = leftChild
                    return localMin?.balance()
                }
            }
            return node.balance()
        }

        fun insertNode(node: Node<K, V>?, key: K, value: V): Node<K, V>? {
            if (node == null) {
                return Node(key, value)
            }
            if (key < node.key) {
                node.leftChild = insertNode(node.leftChild, key, value)
            } else {
                node.rightChild = insertNode(node.rightChild, key, value)
            }
            return node.balance()
        }
    }

    override var size: Int = 0

    override fun containsKey(key: K): Boolean {
        return try {
            walk.toSpecificNodeFromRoot(key, { it.key == key }) ?: return false
            true
        } catch (exception: IndexOutOfBoundsException) {
            false
        }
    }

    override fun containsValue(value: V): Boolean { //need to ve implemented
        val values = this.values
        for (i in values) {
            if (i == value) {
                return true
            }
        }
        return false
    }

    override fun get(key: K): V? {
        val node = walk.toSpecificNodeFromRoot(key, { it.key == key })
            ?: throw IndexOutOfBoundsException("Don't have this item in a set")
        return node.value
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    fun put(key: K, value: V) {
        if (!containsKey(key)) {
            root = manipulator.insertNode(root, key, value)
            size++
        }
    }

    fun remove(key: K) {
        if (containsKey(key)) {
            root = manipulator.removeNode(root, key)
        }
    }

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val entries = emptySet<Entry<K, V>>().toMutableSet()
            val currentRoot = root ?: return entries
            walk.everywhereAndDo(
                currentRoot,
                { node: Node<K, V>, entries: MutableSet<Entry<K, V>>? -> entries?.add(node.toEntry()) }, entries
            )
            return entries.toSet()
        }
    override val keys: Set<K>
        get() {
            val keys = emptySet<K>().toMutableSet()
            val currentRoot = root ?: return keys
            walk.everywhereAndDo(
                currentRoot,
                { node: Node<K, V>, set: MutableSet<K>? -> set?.add(node.key) }, keys
            )
            return keys
        }
    override val values: Collection<V>
        get() {
            val values = emptyList<V>().toMutableList()
            val currentRoot = root ?: return values
            walk.everywhereAndDo(
                currentRoot,
                { node: Node<K, V>, set: MutableList<V>? -> set?.add(node.value) }, values
            )
            return values
        }

    override fun toString(): String {
        var stringAVL = "{"
        for (entry in entries) {
            stringAVL += entry.toString()
        }
        stringAVL += '}'
        return stringAVL
    }
}