package io.github.marmer

data class Galgenmaennchen(val gesuchtesWort: String) {
    private var charsTried: Set<Char> = emptySet()

    private constructor(gesuchtesWort: String, charsTried: Set<Char>) : this(gesuchtesWort) {
        this.charsTried = charsTried
    }

    fun rateBuchstabe(buchstabe: Char): Galgenmaennchen =
        Galgenmaennchen(gesuchtesWort, charsTried.union(setOf(buchstabe.toLowerCase(), buchstabe.toUpperCase())))

    override fun toString(): String = gesuchtesWort
        .map { if (charsTried.contains(it)) it else '-' }
        .joinToString(separator = "")
}
