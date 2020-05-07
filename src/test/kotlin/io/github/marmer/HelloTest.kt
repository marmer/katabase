package io.github.marmer

import org.junit.jupiter.api.Test

class HelloTest {

    @Test
    fun helloTest() {
        someKotlinFunction(arrayOf("Hundekuchen", "Mettbrötchen"))
        HelloJava.someStaticJavaMethod("foo", "bar")
    }
}
