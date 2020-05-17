package spbuhomework.hwAVL

import kotlin.IndexOutOfBoundsException

class AVL<K : Comparable<K>, V> : Map<K, V> {

    private var root: Node<K, V>? = null

    private fun walkToSpecificNode(currentNode: Node<K, V>, key: K, stopFun: (Node<K, V>) -> Boolean): Node<K, V> {
        val leftChild = currentNode.leftChild
        val rightChild = currentNode.rightChild
        return if (stopFun(currentNode)) {
            currentNode
        } else if (key > currentNode.key && rightChild != null) {
            walkToSpecificNode(rightChild, key, stopFun)
        } else if (key < currentNode.key && leftChild != null) {
            walkToSpecificNode(leftChild, key, stopFun)
        } else {
            throw IndexOutOfBoundsException("This node does not exist")
        }
    }

    private fun walkToSpecificNodeFromRoot(key: K, stopFun: (Node<K, V>) -> Boolean): Node<K, V> {
        val startingNode = root ?: throw IndexOutOfBoundsException("Trying to find an item from an empty set")
        return walkToSpecificNode(startingNode, key, stopFun)
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

    private fun setChild(newChild: Node<K, V>, parent: Node<K, V>?) {
        if (parent != null) {
            if (newChild.key > parent.key) {
                parent.rightChild = newChild
            } else if (newChild.key < parent.key) {
                parent.leftChild = newChild
            }
        } else {
            root = newChild
        }
        newChild.parent = parent
    }

    private var dynamicSize: Int = 0

    override val size: Int
        get() = dynamicSize

    override fun containsKey(key: K): Boolean {
        return try {
            walkToSpecificNodeFromRoot(key, { it.key == key })
            true
        } catch (exception: IndexOutOfBoundsException) {
            false
        }
    }

    override fun containsValue(value: V): Boolean { //need to ve implemented
        return false
    }

    override fun get(key: K): V? {
        return try {
            val node = walkToSpecificNodeFromRoot(key, { it.key == key })
            node.value
        } catch (exception: IndexOutOfBoundsException) {
            throw IndexOutOfBoundsException("There is no value with this key")
        }
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }


    fun put(key: K, value: V): V? {
        dynamicSize++
        val currentRoot = root
        if (currentRoot == null) {
            root = Node(key, value)
        } else {
            try {
                val newParent = walkToSpecificNodeFromRoot(
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
        }
        return value
    }

    fun remove(key: K): V? {
        val deletedNode: Node<K, V>

        try {
            deletedNode = walkToSpecificNodeFromRoot(key, { it.key == key })
        } catch (exception: IndexOutOfBoundsException) {
            throw IndexOutOfBoundsException("An item with this key does not exist")
        }

        val deletedNodeParent = deletedNode.parent
        if (deletedNodeParent == null) {
            root = null
        }
        val deletedNodeLeftChild = deletedNode.leftChild
        val deletedNodeRightChild = deletedNode.rightChild
        val deletedNodeValue = deletedNode.value

        if (deletedNode.isLeaf()) {
            deletedNode.delete()
        } else if (deletedNodeLeftChild != null && deletedNodeRightChild == null) {
            setChild(deletedNodeLeftChild, deletedNodeParent)
            deletedNode.delete()
        } else if (deletedNodeLeftChild == null && deletedNodeRightChild != null) {
            setChild(deletedNodeRightChild, deletedNodeParent)
            deletedNode.delete()
        } else if (deletedNodeLeftChild != null && deletedNodeRightChild != null) {
            val localMax = walkToSpecificNode(deletedNodeLeftChild, deletedNode.key, { it.rightChild == null })
            val localMaxValue = localMax.value
            val localMaxKey = localMax.key
            remove(localMaxKey)
            deletedNode.value = localMaxValue
            deletedNode.key = localMaxKey
            dynamicSize++
        }
        dynamicSize--
        return deletedNodeValue
    }

    override val entries: Set<Map.Entry<K, V>>
        get() {
            val entries = emptySet<Entry<K, V>>().toMutableSet()
            val currentRoot = root ?: return entries
            walkEverywhereAndDo(
                currentRoot,
                {node: Node<K, V>,  entries: MutableSet<Entry<K, V>>? -> entries?.add(node.toEntry()) }, entries)
            return entries.toSet()
        }
    override val keys: Set<K>
        get() {
            val keys = emptySet<K>().toMutableSet()
            val currentRoot = root ?: return keys
            walkEverywhereAndDo(
                currentRoot,
                {node: Node<K, V>,  set: MutableSet<K>? -> set?.add(node.key) }, keys)
            return keys
        }
    override val values: Collection<V>
        get() {
            val values = emptyList<V>().toMutableList()
            val currentRoot = root ?: return values
            walkEverywhereAndDo(
                currentRoot,
                {node: Node<K, V>,  set: MutableList<V>? -> set?.add(node.value) }, values)
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