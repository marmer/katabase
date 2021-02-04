package io.github.marmer

sealed class FizzleRules {
    abstract fun canBeApplied(value: Int): Boolean
    abstract fun apply(value: Int): String

    companion object {
        val allRules = listOf(FizzRule(), BuzzRule(), PrimeRule())
    }
}

private class PrimeRule : FizzleRules() {
    override fun canBeApplied(value: Int): Boolean =
        listOf(
            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97
        ).contains(value)

    override fun apply(value: Int): String = "prime"
}

private class FizzRule : FizzleRules() {
    override fun canBeApplied(value: Int): Boolean =
        value % 2 == 0

    override fun apply(value: Int): String = "fizz"
}

private class BuzzRule : FizzleRules() {
    override fun canBeApplied(value: Int): Boolean =
        value % 3 == 0

    override fun apply(value: Int): String = "buzz"
}

