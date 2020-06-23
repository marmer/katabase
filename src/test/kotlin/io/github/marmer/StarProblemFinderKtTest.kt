package io.github.marmer

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class StarProblemFinderKtTest {
    @Test
    @Throws(Exception::class)
    fun `stars with less then five corners should be get a defect`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(corners = 4))

        // Assertion
        assertEquals(listOf(DefectDescriptor("not enough corners")), result)
    }

    @Test
    @Throws(Exception::class)
    fun `stars with exactly five corners should not be a problem`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(corners = 5))

        // Assertion
        assertEquals(emptyList(), result)
    }

    @Test
    @Throws(Exception::class)
    fun `stars with more than five corners and dividable by three should not be a problem`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(corners = 6))

        // Assertion
        assertEquals(emptyList(), result)
    }

    @Test
    @Throws(Exception::class)
    fun `stars with more than five corners and not dividable by three should not be a problem`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(corners = 7))

        // Assertion
        assertEquals(
            listOf(DefectDescriptor("star corners have to be dividable by three if they are bigger than five")),
            result
        )
    }

    @Test
    @Throws(Exception::class)
    fun `light intensity of less than 60 should be a problem`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(lightIntensity = 59))

        // Assertion
        assertEquals(
            listOf(DefectDescriptor("light intensity is too damn low")),
            result
        )
    }

    @Test
    @Throws(Exception::class)
    fun `light intensity from 60 should not be a problem`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(lightIntensity = 60))

        // Assertion
        assertEquals(
            emptyList(),
            result
        )
    }

    @Test
    @Throws(Exception::class)
    fun `it should be possible to get multiple errors`() {
        // Preparation

        // Execution
        val result = findProblemsForStar(perfectStarWith(corners = 4, lightIntensity = 59))

        // Assertion
        assertEquals(
            listOf(
                DefectDescriptor("not enough corners"),
                DefectDescriptor("light intensity is too damn low")
            ),
            result
        )
    }


    private fun perfectStarWith(corners: Int = 5, lightIntensity: Int = 60) = Star(corners, lightIntensity)
}
