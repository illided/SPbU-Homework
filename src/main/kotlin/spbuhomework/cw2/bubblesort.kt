package spbuhomework.cw2

fun <T> bubbleSort(array: Array<T>, comparator: Comparator<T>): Array<T> {
    var swap = true
    while (swap) {
        swap = false
        for (i in 0 until array.size - 1) {
            if (comparator.compare(array[i], array[i + 1]) > 0) {
                array[i] = array[i + 1].also {
                    array[i + 1] = array[i]
                    swap = true
                }
            }
        }
    }
    return array
}
