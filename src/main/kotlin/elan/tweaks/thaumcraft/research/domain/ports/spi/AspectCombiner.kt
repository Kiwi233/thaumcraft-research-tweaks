package elan.tweaks.thaumcraft.research.domain.ports.spi

import thaumcraft.api.aspects.Aspect

interface AspectCombiner {
    fun combine(firstAspect: Aspect, secondAspect: Aspect): Result<Unit>
}