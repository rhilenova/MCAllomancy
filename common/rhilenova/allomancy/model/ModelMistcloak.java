package rhilenova.allomancy.model;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import rhilenova.allomancy.Reference;
import cpw.mods.fml.client.FMLClientHandler;

public class ModelMistcloak extends ModelBiped
{
    private List<Tassle> tassle_list;

    public ModelMistcloak()
    {
        tassle_list = new ArrayList<Tassle>();
        // Front Right
        for (int x = 0; x < 3; ++x)
        {
            tassle_list.add(new Tassle(-0.6f + (x*0.17), 0.3f, -0.2f, 0.17, 0));
        }
        // Front Left
        for (int x = 4; x < 7; ++x)
        {
            tassle_list.add(new Tassle(-0.6f + (x*0.17), 0.3f, -0.2f, 0.17, 0));
        }
        // Back
        for (int x = 0; x < 7; ++x)
        {
            tassle_list.add(new Tassle(-0.6f + (x*0.17), 0.3f, 0.2f, 0.17, 2));
        }
        // Right
        for (int x = 0; x < 2; ++x)
        {
            tassle_list.add(new Tassle(-0.6f, 0.3f, -0.2f + (x*0.2), 0.2, 1));
        }
        // Left
        for (int x = 0; x < 2; ++x)
        {
            tassle_list.add(new Tassle(0.6f, 0.3f, -0.2f + (x*0.2), 0.2, 3));
        }
    }

    @Override
    public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Reference.Textures.MISTCLOAK);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(255, 255, 255, 255);
        // Top
        tessellator.addVertexWithUV(-0.6f, -0.06f, -0.2f, 0.0f, 0.0f);
        tessellator.addVertexWithUV(-0.6f, -0.06f, 0.2f, 0.125f, 0.0f);
        tessellator.addVertexWithUV(0.6f, -0.06f, 0.2f, 0.125f, 0.875f);
        tessellator.addVertexWithUV(0.6f, -0.06f, -0.2f, 0.0f, 0.875f);
        // Front
        tessellator.addVertexWithUV(-0.6f, -0.06f, -0.2f, 0.125f, 0.0f);
        tessellator.addVertexWithUV(-0.6f, 0.3f, -0.2f, 0.25f, 0.0f);
        tessellator.addVertexWithUV(0.6f, 0.3f, -0.2f, 0.25f, 0.875f);
        tessellator.addVertexWithUV(0.6f, -0.06f, -0.2f, 0.125f, 0.875f);
        //Back
        tessellator.addVertexWithUV(-0.6f, -0.06f, 0.2f, 0.0f, 0.0f);
        tessellator.addVertexWithUV(-0.6f, 0.3f, 0.2f, 0.125f, 0.0f);
        tessellator.addVertexWithUV(0.6f, 0.3f, 0.2f, 0.125f, 0.875f);
        tessellator.addVertexWithUV(0.6f, -0.06f, 0.2f, 0.0f, 0.875f);
        // Right
        tessellator.addVertexWithUV(-0.6f, -0.06f, 0.2f, 0.125f, 0.0f);
        tessellator.addVertexWithUV(-0.6f, 0.3f, 0.2f, 0.25f, 0.0f);
        tessellator.addVertexWithUV(-0.6f, 0.3f, -0.2f, 0.25f, 0.25f);
        tessellator.addVertexWithUV(-0.6f, -0.06f, -0.2f, 0.125f, 0.25f);
        // Left
        tessellator.addVertexWithUV(0.6f, -0.06f, 0.2f, 0.125f, 0.0f);
        tessellator.addVertexWithUV(0.6f, 0.3f, 0.2f, 0.25f, 0.0f);
        tessellator.addVertexWithUV(0.6f, 0.3f, -0.2f, 0.25f, 0.25f);
        tessellator.addVertexWithUV(0.6f, -0.06f, -0.2f, 0.125f, 0.25f);
        for (int x = 0; x < tassle_list.size(); ++x)
        {
            tassle_list.get(x).render(entity, tessellator);
        }
        tessellator.draw();
    }
}
