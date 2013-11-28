package rhilenova.allomancy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import rhilenova.allomancy.item.ItemManager;
import rhilenova.allomancy.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION_NUMBER)
//@NetworkMod(channels = {Reference.MOD_CHANNEL}, clientSideRequired = true, serverSideRequired = true, packetHandler = PacketHandler.class)
public class Allomancy
{
	@Instance(Reference.MOD_ID)
    public static Allomancy instance;
	
	public static CreativeTabs tabsAllomancy =
        new CreativeTabAllomancy(CreativeTabs.getNextID(), Reference.MOD_ID);
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
	{
	    Configuration config = new Configuration(event.getSuggestedConfigurationFile());
	    config.load();
	    Reference.ItemIds.allomancers_vial =
	            config.getItem("allomancers_vial", Reference.ItemIds.allomancers_vial_default).getInt();
	    Reference.ItemIds.mistcloak =
                config.getItem("mistcloak", Reference.ItemIds.mistcloak_default).getInt();
	    config.save();
	    
	    ItemManager.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    proxy.initRenderers();
	}
}
