package io.github.marmer

import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito

internal class OhceApplicationTest {

    //dependencies
    private val inputProviderMock = mock<InputProvider>()
    private val outputProcessorMock = mock<OutputProcessor>()
    private val greetingProviderMock = mock<GreetingProvider>()

    //initialization
    private val underTest = OhceApplication(inputProviderMock, outputProcessorMock, greetingProviderMock)

    @Test
    @Throws(Exception::class)
    fun `The console shold serve the reverse input of the given one`() {
        // Preparation
        Mockito.`when`(inputProviderMock()).thenReturn("sample input", "Stop!")

        // Execution
        underTest.start()

        // Assertion
        verify(outputProcessorMock)("tupni elpmas")
    }

    @Test
    @Throws(Exception::class)
    fun `The console should be happy about palindromes after printing its reverse input`() {
        // Preparation
        Mockito.`when`(inputProviderMock()).thenReturn("anna", "Stop!")

        // Execution
        underTest.start()

        // Assertion
        outputProcessorMock.inOrder { verify()("anna") }
        outputProcessorMock.inOrder { verify()("¡Bonita palabra!") }
    }

    @Test
    @Throws(Exception::class)
    fun `multiple inputs should be processed`() {
        // Preparation
        Mockito.`when`(inputProviderMock()).thenReturn("sample input", "another input", "Stop!")

        // Execution
        underTest.start()

        // Assertion
        outputProcessorMock.inOrder { verify()("tupni elpmas") }
        outputProcessorMock.inOrder { verify()("tupni rehtona") }
    }

    @Test
    @Throws(Exception::class)
    fun `The application should stop when the keyword stop was entered and say good bye to the one who started it`() {
        Mockito.`when`(inputProviderMock()).thenReturn("Stop!")

        // Execution
        underTest.start("Superman")

        // Assertion
        verify(outputProcessorMock)("Adios Superman")
    }

    @Test
    @Throws(Exception::class)
    fun `The application should greet you with your name when it starts`() {
        // Preparation
        Mockito.`when`(inputProviderMock()).thenReturn("Stop!")
        Mockito.`when`(greetingProviderMock.greetingFor("Superman")).thenReturn("Buenos dias Superman")


        // Execution
        underTest.start("Superman")

        // Assertion
        outputProcessorMock.inOrder { verify()("Buenos dias Superman") }
    }
}

