package elan.tweaks.thaumcraft.research.integration.client.gui

import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.client.multiplayer.WorldClient
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

class GuiHandler : IGuiHandler {

    override fun getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? =
        if (id == IDs.RESEARCH_TABLE) {
            ResearchTableGuiFactory.createContainer(player, world, x, y, z)
        } else {
            null
        }

    override fun getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? =
        if (world is WorldClient && id == IDs.RESEARCH_TABLE)
            ResearchTableGuiFactory.createFor(player, world, x, y, z)
        else null
    
    object IDs {
        const val RESEARCH_TABLE = 0
    }
}
