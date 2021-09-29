package elan.tweaks.thaumcraft.research.integration.table.gui.component

import cpw.mods.fml.client.FMLClientHandler
import elan.tweaks.common.gui.component.UIContext
import elan.tweaks.common.gui.component.dragndrop.DragAndDropUIComponent
import elan.tweaks.common.gui.geometry.VectorXY
import elan.tweaks.thaumcraft.research.domain.ports.provided.AspectPalletPort
import net.minecraft.client.renderer.RenderHelper
import org.lwjgl.opengl.GL11
import thaumcraft.api.aspects.Aspect
import thaumcraft.client.fx.ParticleEngine
import thaumcraft.client.lib.UtilsFX
import thaumcraft.common.config.Config
import java.awt.Color

class AspectDragAndDropUIComponent(
    private val pallet: AspectPalletPort
) : DragAndDropUIComponent {
    private var draggable: Aspect? = null

    override fun onAttemptDrag(draggable: Any, uiMousePosition: VectorXY, context: UIContext) {
        if (this.draggable != null || draggable !is Aspect) return
        
        this.draggable = draggable
    }

    override fun onDropping(context: UIContext): Any? {
        val droppedAspect = draggable ?: return null

        draggable = null
        return droppedAspect
    }

    override fun onDragClick(context: UIContext): Any? = 
        draggable

    override fun onDragging(uiMousePosition: VectorXY, context: UIContext) {
        val draggedAspect = draggable ?: return
        if(pallet.isDrainedOf(draggedAspect)) {
            draggable = null
            return
        }
            
        drawOrb(uiMousePosition, draggedAspect.color, context)
    }

    private fun drawOrb(uiOrigin: VectorXY, color: Int, context: UIContext) {
        val origin = context.toScreenOrigin(uiOrigin)

        GL11.glPushMatrix()

        RenderHelper.disableStandardItemLighting()
        GL11.glEnable(GL11.GL_BLEND)
        
        val count = FMLClientHandler.instance().client.thePlayer.ticksExisted
        val c = Color(color)
        var red = c.red.toFloat() / 255.0f
        var green = c.green.toFloat() / 255.0f
        var blue = c.blue.toFloat() / 255.0f
        if (Config.colorBlind) {
            red /= 1.8f
            green /= 1.8f
            blue /= 1.8f
        }
        UtilsFX.bindTexture(ParticleEngine.particleTexture)
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        GL11.glTranslated(origin.x - 8.0, origin.y - 8.0, 0.0)
        val part = count % 8
        val var8 = 0.5f + part.toFloat() / 8.0f
        val var9 = var8 + 0.0624375f
        val var10 = 0.5f
        val var11 = var10 + 0.0624375f
        context.drawQuads {
            setBrightness(240)
            setColorRGBA_F(red, green, blue, 1.0f)
            addVertexWithUV(0.0, 16.0, origin.z, var9.toDouble(), var11.toDouble())
            addVertexWithUV(16.0, 16.0, origin.z, var9.toDouble(), var10.toDouble())
            addVertexWithUV(16.0, 0.0, origin.z, var8.toDouble(), var10.toDouble())
            addVertexWithUV(0.0, 0.0, origin.z, var8.toDouble(), var11.toDouble())
        }
        GL11.glPopMatrix()

    }
}
