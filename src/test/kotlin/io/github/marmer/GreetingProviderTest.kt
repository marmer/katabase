package io.github.marmer

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.spy
import org.junit.jupiter.api.Test
import java.time.LocalTime
import kotlin.test.assertEquals

internal class GreetingProviderTest {
    private var systemTimeProviderMock = spy<SystemTimeProvider>()

    private var underTest: GreetingProvider = GreetingProvider(systemTimeProviderMock)

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos dias at beginning of the morning with a name`() {
        // Preparation
        doReturn(LocalTime.of(6, 0)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos dias Bart")
    }

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos dias at the ending of morning with a name`() {
        // Preparation
        doReturn(LocalTime.of(11, 59, 59, 99999)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos dias Bart")
    }

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos tarde at beginning of the evening with a name`() {
        // Preparation
        doReturn(LocalTime.of(12, 0)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos tarde Bart")
    }

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos tarde at the ending of evening with a name`() {
        // Preparation
        doReturn(LocalTime.of(19, 59, 59, 99999)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos tarde Bart")
    }

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos noche at beginning of the night with a name`() {
        // Preparation
        doReturn(LocalTime.of(20, 0)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos noche Bart")
    }

    @Test
    @Throws(Exception::class)
    fun `The answer should contain buenos noche at the ending of night with a name`() {
        // Preparation
        doReturn(LocalTime.of(5, 59, 59, 99999)).`when`(systemTimeProviderMock).currentTime()

        // Execution
        val result = underTest.greetingFor("Bart")

        // Assertion
        assertEquals(result, "Buenos noche Bart")
    }

}
