package elan.tweaks.thaumcraft.research.integration.aspect.pool

import elan.tweaks.thaumcraft.research.domain.ports.spi.KnowledgeBase
import thaumcraft.common.lib.research.ResearchManager

class KnowledgeBaseAdapter(
    private val playerCommandSenderName: String
) : KnowledgeBase {
    companion object {
        private const val RESEARCH_EXPERTISE = "RESEARCHER1"
        private const val RESEARCH_MASTERY = "RESEARCHER2"
    }

    override fun notDiscoveredResearchExpertise(): Boolean =
        !hasDiscovered(RESEARCH_EXPERTISE)

    override fun notDiscoveredResearchMastery(): Boolean =
        !hasDiscovered(RESEARCH_MASTERY)

    private fun hasDiscovered(researchName: String) = 
        ResearchManager.isResearchComplete(playerCommandSenderName, researchName)

}
