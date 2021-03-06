package spbuhomework.hwAVL

import kotlin.IndexOutOfBoundsException

class AVL<K : Comparable<K>, V> : Map<K, V> {

    private var root: Node<K, V>? = null
    private val walk = Walker()
    private val manipulator = Manipulator()

    private inner class Walker {

        fun toSpecificNode(
            key: K,
            stopFun: (Node<K, V>) -> Boolean,
            currentNode: Node<K, V>? = root
        ): Node<K, V>? {
            val leftChild = currentNode?.leftChild
            val rightChild = currentNode?.rightChild
            return if (currentNode == null || stopFun(currentNode)) {
                currentNode
            } else if (key > currentNode.key) {
                toSpecificNode(key, stopFun, rightChild)
            } else {
                toSpecificNode(key, stopFun, leftChild)
            }
        }

        fun <T> everywhereAndDo(
            currentNode: Node<K, V>?,
            action: (Node<K, V>, T?) -> Unit,
            additional: T? = null
        ) {
            if (currentNode == null) {
                return
            }
            everywhereAndDo(currentNode.leftChild, action, additional)
            action(currentNode, additional)
            everywhereAndDo(currentNode.rightChild, action, additional)
        }
    }

    private inner class Manipulator {

        private fun removeMin(node: Node<K, V>): Node<K, V>? {
            val leftChild = node.leftChild ?: return node.rightChild
            node.leftChild = removeMin(leftChild)
            return node.balance()
        }

        fun removeNode(node: Node<K, V>, key: K): Node<K, V>? {
            val outputNode: Node<K, V>?
            when {
                key < node.key -> node.leftChild = node.leftChild?.let { removeNode(it, key) }
                key > node.key -> node.rightChild = node.rightChild?.let { removeNode(it, key) }
                else -> {
                    val leftChild = node.leftChild
                    val rightChild = node.rightChild
                    if (rightChild == null) {
                        outputNode = leftChild
                    } else {
                        val localMin = walk.toSpecificNode(key, { it.leftChild == null }, rightChild)
                        localMin?.rightChild = removeMin(rightChild)
                        localMin?.leftChild = leftChild
                        outputNode = localMin?.balance()
                    }
                    return outputNode
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

    override fun containsKey(key: K): Boolean = walk.toSpecificNode(key, { it.key == key }) != null

    override fun containsValue(value: V): Boolean = values.contains(value)

    override fun get(key: K): V? {
        val node = walk.toSpecificNode(key, { it.key == key })
            ?: throw IndexOutOfBoundsException("Don't have this item in AVL")
        return node.value
    }

    override fun isEmpty(): Boolean = size == 0

    fun put(key: K, value: V) {
        if (!containsKey(key)) {
            root = manipulator.insertNode(root, key, value)
            size++
        }
    }

    fun remove(key: K) {
        if (containsKey(key)) {
            val currentRoot = root
            root = currentRoot?.let { manipulator.removeNode(it, key) }
            size--
        } else {
            throw IndexOutOfBoundsException("Item with this key does not exist")
        }
    }

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val entries = emptySet<Entry<K, V>>().toMutableSet()
            val currentRoot = root
            walk.everywhereAndDo(
                currentRoot,
                { node: Node<K, V>, currentEntries: MutableSet<Entry<K, V>>? -> currentEntries?.add(node.toEntry()) },
                entries
            )
            return entries.toSet()
        }
    override val keys: Set<K>
        get() {
            val keys = emptySet<K>().toMutableSet()
            val currentRoot = root
            walk.everywhereAndDo(
                currentRoot,
                { node: Node<K, V>, set: MutableSet<K>? -> set?.add(node.key) }, keys
            )
            return keys
        }
    override val values: Collection<V>
        get() {
            val values = emptyList<V>().toMutableList()
            val currentRoot = root
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
