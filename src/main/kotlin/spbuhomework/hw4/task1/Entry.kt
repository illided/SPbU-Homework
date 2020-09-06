package spbuhomework.hw4.task1

class Entry<K, V>(override val key: K, override val value: V) : Map.Entry<K, V> {
    override fun toString(): String {
        return "($key : $value)"
    }
}
