package io.github.marmer

data class ClosedRange(val start: Int, val end: Int) {
    fun toFizzeles(): List<String> =
        (start..end).map { it.toFizzeled() }
}

private fun Int.toFizzeled(): String =
    with(FizzleRules.allRules.filter { it.canBeApplied(this) }) {
        return if (this.isEmpty()) {
            "${this@toFizzeled}"
        } else {
            this.joinToString("") { it.apply(this@toFizzeled) }
        }
    }
