package io.github.marmer

private val defectMap: Map<(Star) -> Boolean, (Star) -> DefectDescriptor> =
    mapOf(
        Pair(
            { (corners) -> corners < 5 },
            { _ -> DefectDescriptor("not enough corners") }),
        Pair(
            { (corners) -> corners > 5 && corners % 3 != 0 },
            { _ -> DefectDescriptor("star corners have to be dividable by three if they are bigger than five") }),
        Pair(
            { (_, lightIntensity) -> lightIntensity < 60 },
            { _ -> DefectDescriptor("light intensity is too damn low") })

    )

fun findProblemsForStar(star: Star): List<DefectDescriptor> =
    defectMap
        .filter { (hasDefect) -> hasDefect(star) }
        .map { (_, defectFor) -> defectFor(star) }
