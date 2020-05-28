package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

typealias CellGrid = Array<Array<Boolean>>

internal class GameOfLifeKtEvolveTest {

    @Test
    @Throws(Exception::class)
    fun `toCellString - Living cells should be represented correctly`() {
        // Preparation

        // Execution
        val result = arrayOf(arrayOf(true)).toCellString()

        // Assertion
        assertThat(result).`as`("living cell").isEqualTo("*")
    }

    @Test
    @Throws(Exception::class)
    fun `toCellString - Dead cells should be represented correctly`() {
        // Preparation

        // Execution
        val result = arrayOf(arrayOf(false)).toCellString()

        // Assertion
        assertThat(result).`as`("living cell").isEqualTo(".")
    }

    @Test
    @Throws(Exception::class)
    fun `toCellString - Multiple rows and lines should be represented as such within the resulting string`() {
        // Preparation

        // Execution
        val result = arrayOf(
            arrayOf(false, true),
            arrayOf(true, false)
        ).toCellString()

        // Assertion
        assertThat(result).`as`("cell grid string").isEqualTo(".*\n*.")
    }

    @Test
    @Throws(Exception::class)
    fun `toCellGrid - Living cells should be represented correctly`() {
        // Preparation

        // Execution
        val result = "*".toCellGrid()

        // Assertion
        assertThat(result).`as`("living cell").isEqualTo(
            arrayOf(arrayOf(true))
        )
    }

    @Test
    @Throws(Exception::class)
    fun `toCellGrid - Dead cells should be represented correctly`() {
        // Preparation

        // Execution
        val result = ".".toCellGrid()

        // Assertion
        assertThat(result).`as`("living cell").isEqualTo(
            arrayOf(arrayOf(false))
        )
    }

    @Test
    @Throws(Exception::class)
    fun `toCellGrid - Multiple rows and lines should be represented as such within the resulting string`() {
        // Preparation

        // Execution
        val result = """.*
*.""".toCellGrid()

        // Assertion
        assertThat(result).`as`("cell grid string").isEqualTo(
            arrayOf(
                arrayOf(false, true),
                arrayOf(true, false)
            )
        )
    }

    @Test
    @Throws(Exception::class)
    fun `evolve - should serve a an evolved cell grid`() {
        // Preparation
        val grid = arrayOf(
            arrayOf(false, true),
            arrayOf(false, true),
            arrayOf(false, true)
        )

        // Execution
        val result = grid.evolve()

        // Assertion
        assertThat(result).isNotSameAs(grid)
            .isEqualTo(
                arrayOf(
                    arrayOf(false, false),
                    arrayOf(true, true),
                    arrayOf(false, false)
                )
            )
    }

    companion object {
        @JvmStatic
        fun livingCellRules() = arrayOf(
            LivingCellRule(wasAlive = false, neighbourCount = 0, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = false, neighbourCount = 1, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = false, neighbourCount = 2, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = false, neighbourCount = 3, expectedEvolutionResult = true),
            LivingCellRule(wasAlive = false, neighbourCount = 4, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = true, neighbourCount = 0, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = true, neighbourCount = 1, expectedEvolutionResult = false),
            LivingCellRule(wasAlive = true, neighbourCount = 2, expectedEvolutionResult = true),
            LivingCellRule(wasAlive = true, neighbourCount = 3, expectedEvolutionResult = true),
            LivingCellRule(wasAlive = true, neighbourCount = 4, expectedEvolutionResult = false)
        )
    }

    @ParameterizedTest
    @MethodSource("livingCellRules")
    @Throws(Exception::class)
    fun `isAliveAfterEvolution - should serve the expected result whether a cell has to live or not`(livingCellRule: LivingCellRule) {
        // Preparation
        val (wasAlive, neighbourCount, expectedEvolutionResult) = livingCellRule

        // Execution
        val result = isAliveAfterAvolution(wasAlive, neighbourCount)

        // Assertion
        assertThat(result).`as`("expectedEvolutionResult").isEqualTo(expectedEvolutionResult)
    }

    internal data class LivingCellRule(
        val wasAlive: Boolean,
        val neighbourCount: Int,
        val expectedEvolutionResult: Boolean
    )

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should count all living neighbours`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(1, 1)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(8)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should not count dead cells neighbours`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(false, false, false),
            arrayOf(false, true, false),
            arrayOf(false, false, false)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(1, 1)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(0)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the top left corner`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(0, 0)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(3)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the top right corner`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(0, 2)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(3)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the bottom left corner`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(2, 0)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(3)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the bottom right corner`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(2, 2)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(3)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the top border`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(0, 1)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(5)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the right border`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(1, 2)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(5)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the left border`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(1, 0)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(5)
    }

    @Test
    @Throws(Exception::class)
    fun `getLivingNeighbourCountFor - should be able to count at the bottom border`() {
        // Preparation
        val cellGrid = arrayOf(
            arrayOf(true, true, true),
            arrayOf(true, true, true),
            arrayOf(true, true, true)
        )

        // Execution
        val result = cellGrid.getLivingNeighbourCountFor(2, 1)

        // Assertion
        assertThat(result).`as`("Neighbours of relevant cells").isEqualTo(5)
    }
}
