package rhilenova.allomancy.client.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import rhilenova.allomancy.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ItemAllomancersVialRenderer implements IItemRenderer
{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper)
    {
        return type == IItemRenderer.ItemRenderType.ENTITY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Reference.Textures.ALLOMANCERS_VIAL);
        Tessellator tessellator = Tessellator.instance;
        int metal = item.getItemDamage() - 1;
        
        if (type == IItemRenderer.ItemRenderType.INVENTORY)
        {
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV(0, 0, 0, 0, 0);
            tessellator.addVertexWithUV(0, 16, 0, 0, 0.125);
            tessellator.addVertexWithUV(16, 16, 0, 0.125, 0.125);
            tessellator.addVertexWithUV(16, 0, 0, 0.125, 0);
            tessellator.draw();
            
            if (metal >= 0)
            {
                double startX = (metal % 8.0) * 0.125;
                double startY = Math.floor(metal / 8.0) * 0.125 + 0.125;
                tessellator.startDrawingQuads();
                tessellator.addVertexWithUV(0, 0, 0, startX, startY);
                tessellator.addVertexWithUV(0, 16, 0, startX, startY + 0.125);
                tessellator.addVertexWithUV(16, 16, 0, startX + .125, startY + 0.125);
                tessellator.addVertexWithUV(16, 0, 0, startX + .125, startY);
                tessellator.draw();
            }
        }
        else if (type == IItemRenderer.ItemRenderType.ENTITY)
        {
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(-0.5f, 0.0f, 0.0f);
            
            if (item.getItemFrame() != null)
            {
                GL11.glTranslatef(0.0f, -0.5f, 0.0f);
            }
            
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 0.125F, 0.125F, 16, 16, 0.0625F);
            
            if (metal >= 0)
            {
                double startX = (metal % 8.0) * 0.125;
                double startY = Math.floor(metal / 8.0) * 0.125 + 0.125;
                ItemRenderer.renderItemIn2D(tessellator, (float)startX, (float)startY, (float)startX + 0.125F,
                                            (float)startY + 0.125F, 16, 16, 0.0625F);
            }
        }
        else if (type == IItemRenderer.ItemRenderType.EQUIPPED ||
                 type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 0.125F, 0.125F, 16, 16, 0.0625F);
            
            if (metal >= 0)
            {
                double startX = (metal % 8.0) * 0.125;
                double startY = Math.floor(metal / 8.0) * 0.125 + 0.125;
                ItemRenderer.renderItemIn2D(tessellator, (float)startX, (float)startY, (float)startX + 0.125F,
                                            (float)startY + 0.125F, 16, 16, 0.0625F);
            }
        }
    }
}
