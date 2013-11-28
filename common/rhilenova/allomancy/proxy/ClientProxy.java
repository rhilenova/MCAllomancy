package rhilenova.allomancy.proxy;

import net.minecraftforge.client.MinecraftForgeClient;
import rhilenova.allomancy.Reference;
import rhilenova.allomancy.client.renderer.ItemAllomancersVialRenderer;

public class ClientProxy extends CommonProxy
{
    @Override
    public void initRenderers()
    {
        MinecraftForgeClient.registerItemRenderer(Reference.ItemIds.allomancers_vial,
                                                  new ItemAllomancersVialRenderer());
    }
}
