package io.github.marmer

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

class ApplicationTest {

    /**
     * Find the biggest difference between the string length of two lists
     */
    @TestFactory
    fun simpleNumbers() = listOf(
        TestSetup(emptyArray(), arrayOf("aasd"), -1),
        TestSetup(arrayOf("aasd"), emptyArray(), -1),
        TestSetup(arrayOf("aasd"), arrayOf("123"), 1),
        TestSetup(arrayOf("123"), arrayOf("aasd"), 1),
        TestSetup(arrayOf("123"), arrayOf("a"), 2),
        TestSetup(arrayOf("123", "1234"), arrayOf("ab"), 2),
        TestSetup(arrayOf("123"), arrayOf("aasd", "abcde"), 2),
        TestSetup(arrayOf("123"), arrayOf("aas"), 0),
        TestSetup(arrayOf("1234"), arrayOf("aasx"), 0),
    ).map { (a, b, expected) ->
        DynamicTest.dynamicTest("A:$a B:$b Expected $expected") {
            assertEquals(expected, mxdiflg(a, b))
        }
    }

    data class TestSetup(val a: Array<String>, val b: Array<String>, val expected: Int)
}
