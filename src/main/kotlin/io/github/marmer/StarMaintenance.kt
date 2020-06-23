package io.github.marmer

class StarMaintenance(
    private val deliveryService: DeliveryService,
    private val starRepairService: StarRepairService,
    starProblemFinder: (Star) -> List<DefectDescriptor>,
    private val starShop: StarShop
) {
    private val findStarProblemsFor = starProblemFinder

    fun maintain(starCoordinates: String) {
        val star = deliveryService.getStarFrom(starCoordinates)

        val problems = findStarProblemsFor(star)

        val starToSend = when {
            problems.isEmpty() -> star
            problems.size < 2 -> starRepairService.repair(star)
            else -> starShop.buyNewStar()
        }

        deliveryService.sendTo(starCoordinates, starToSend)
    }

}
