package elan.tweaks.thaumcraft.research.domain.ports.spi

interface KnowledgeBase {
    fun notDiscoveredResearchMastery(): Boolean
    fun notDiscoveredResearchExpertise(): Boolean

    companion object {
        const val RESEARCH_MASTERY = "research mastery"
    }
}
