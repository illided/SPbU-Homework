package spbuhomework.hw5.task1

import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

interface Serializable {
    @Throws(IOException::class)
    fun serialize(output: OutputStream)

    @Throws(IOException::class)
    fun deserialize(input: InputStream)
}