package io.github.marmer

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.stubbing.Answer

internal class StarMaintenanceTest {
    private val starShopMock = mock<StarShop>()
    private val starProblemFinderMock =
        mock<(Star) -> List<DefectDescriptor>>(defaultAnswer = Answer { emptyList<DefectDescriptor>() })
    private val starRepairServiceMock = mock<StarRepairService>()
    private val deliveryServiceMock = mock<DeliveryService>()

    private var underTest: StarMaintenance = StarMaintenance(
        deliveryService = deliveryServiceMock,
        starRepairService = starRepairServiceMock,
        starProblemFinder = starProblemFinderMock,
        starShop = starShopMock
    )


    @Test
    @Throws(Exception::class)
    fun `defect stars should get repaired and send back to where they came from`() {
        // Preparation
        val star = defectStar()
        val repairedStar = perfectStar()
        val defects = listOf(newDefect())

        Mockito.`when`(deliveryServiceMock.getStarFrom("P32O")).thenReturn(star)
        Mockito.`when`(starProblemFinderMock.invoke(star)).thenReturn(defects)
        Mockito.`when`(starRepairServiceMock.repair(star)).thenReturn(repairedStar)

        // Execution
        underTest.maintain("P32O")

        // Assertion
        verify(deliveryServiceMock).sendTo("P32O", repairedStar)
    }

    @Test
    @Throws(Exception::class)
    fun `stars with too many defects should be replaced by a new one`() {
        // Preparation
        val star = defectStar()
        val newStar = perfectStar()
        val defects = (1..2).map { newDefect() }

        Mockito.`when`(deliveryServiceMock.getStarFrom("P32O")).thenReturn(star)
        Mockito.`when`(starProblemFinderMock.invoke(star)).thenReturn(defects)
        Mockito.`when`(starShopMock.buyNewStar()).thenReturn(newStar)

        // Execution
        underTest.maintain("P32O")

        // Assertion
        verify(deliveryServiceMock).sendTo("P32O", newStar)
    }

    private fun defectStar() = Star(corners = 4, lightIntensity = 50)

    private fun perfectStar() = Star(corners = 5, lightIntensity = 50)


    @Test
    @Throws(Exception::class)
    fun `Stars without defects should get back to where they came from without to get repaired`() {
        // Preparation
        // Preparation
        val star = perfectStar()

        Mockito.`when`(deliveryServiceMock.getStarFrom("P32O")).thenReturn(star)

        // Execution
        underTest.maintain("P32O")

        // Assertion
        verify(deliveryServiceMock).sendTo("P32O", star)
    }

    private fun newDefect() = DefectDescriptor("something went wrong")

}
