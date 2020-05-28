package io.github.marmer

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class CellTest {

    companion object {
        @JvmStatic
        fun cellData() = arrayOf(
            arrayOf(
                Cell(
                    alive = false,
                    neighbours = listOf(
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = true,
                    neighbours = listOf(
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = false,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = true,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = false),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = false,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = true,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = false),
                        Cell(alive = false)
                    )
                ), true
            ), arrayOf(
                Cell(
                    alive = false,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = false)
                    )
                ), true
            ), arrayOf(
                Cell(
                    alive = true,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = false)
                    )
                ), true
            ),
            arrayOf(
                Cell(
                    alive = false,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true)
                    )
                ), false
            ), arrayOf(
                Cell(
                    alive = true,
                    neighbours = listOf(
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true),
                        Cell(alive = true)
                    )
                ), false
            )

        )
    }

    @ParameterizedTest
    @MethodSource("cellData")
    @Throws(Exception::class)
    fun `evolve - dead cell with no living neighbours should stay dead`(underTest: Cell, isLivingAfterEvolve: Boolean) {
        // Preparation

        // Execution
        val result = underTest.evolve()

        // Assertion
        assertThat(result).extracting("alive").isEqualTo(isLivingAfterEvolve)
    }


}
