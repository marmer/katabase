package io.github.marmer

data class Ringbuffer<T>(var size: Int) {
    var count = 0
        private set

    private val buffer: Array<Any?> = arrayOfNulls(size)

    private var takeIndex = 0
    private var setIndex = 0

    fun add(value: T) {
        buffer[setIndex] = value
        incrementSetIndex()
        incrementCount()
    }

    private fun incrementCount() {
        if (count < size) ++count
    }

    private fun decrementCount() {
        if (count > 0) --count
    }

    private fun incrementSetIndex() {
        if (setIndex == takeIndex && isEmpty()) {
            incrementTakeIndex()
        }
        setIndex = if (setIndex + 1 >= size) 0 else setIndex + 1
    }

    fun isEmpty() = count != 0

    fun add(vararg values: T) {
        values.forEach { add(it) }
    }

    fun take(): T? {
        val retVal = buffer[takeIndex].also { buffer[takeIndex] = null } as T?
        incrementTakeIndex()
        decrementCount()
        return retVal
    }

    private fun incrementTakeIndex() {
        takeIndex = if (takeIndex + 1 >= size) 0 else takeIndex + 1
    }
}
