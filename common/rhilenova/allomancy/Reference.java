package rhilenova.allomancy;

import net.minecraft.util.ResourceLocation;

public class Reference
{
	// @Mod settings.
	public static final String MOD_ID = "allomancy";
	public static final String MOD_NAME = "Allomancy";
	public static final String VERSION_NUMBER = "0.1";
	
	// @NetworkMod settings.
	public final static String MOD_CHANNEL = MOD_ID;
	
	// Proxy settings.
	public static final String CLIENT_PROXY_CLASS = "rhilenova.allomancy.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "rhilenova.allomancy.proxy.CommonProxy";
	
	// Item ID offset.
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;
	
    // Item Ids.
    // 8268-8343 open: http://www.minecraftforge.net/forum/index.php/topic,638.0.html?PHPSESSID=x0hYAwoUh2UXbrDqJXVAa3
	public static class ItemIds
	{
	    public static final int allomancers_vial_default = 8268;
        public static final int mistcloak_default = 8269;
	    
		public static int allomancers_vial;
        public static int mistcloak;
	}
	
	// Names.
	public static final class Strings
	{
		public static final String allomancers_vial = MOD_ID + ":allomancersVial";
	}
	
	// Metal indicies.
	public static final class Metals
	{
	    public static final int empty = 0;
	    public static final int iron = 1;
	    public static final int steel = 2;
	    public static final int tin = 3;
	    public static final int pewter = 4;
	    public static final int zinc = 5;
	    public static final int brass = 6;
	    public static final int copper = 7;
	    public static final int bronze = 8;
	    public static final int gold = 9;
	    public static final int electrum = 10;
	    public static final int chromium = 11;
	    public static final int nicrosil = 12;
	    public static final int aluminum = 13;
	    public static final int duralumin = 14;
	    public static final int cadmium = 15;
	    public static final int bendalloy = 16;
	    public static final int atium = 17;
	    public static final int malatium = 18;
	    public static final int larasium = 19;
	    public static final int mixed = 20;
	}
	
	public static final String MetalNames[] =
        {"empty", "iron", "steel", "tin", "pewter", "zinc", "brass", "copper",
         "bronze", "gold", "electrum", "chromium", "nicrosil", "aluminum",
         "duralumin", "cadmium", "bendalloy", "atium", "malatium", "larasium",
         "mixed"};
	
    public static final class Textures
    {
        public static final ResourceLocation ALLOMANCERS_VIAL =
                new ResourceLocation(MOD_ID, "textures/items/allomancersVial.png");
        public static final ResourceLocation MISTCLOAK =
                new ResourceLocation(MOD_ID, "textures/items/mistcloak.png");
    }
    
    public static final class Models
    {
        public static final String MODEL_LOCATION = "/assets/allomancy/models/";
        public static final String MISTCLOAK = MODEL_LOCATION + "mistcloak.obj";
    }
}
