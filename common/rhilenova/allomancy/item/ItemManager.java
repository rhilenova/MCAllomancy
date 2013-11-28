package rhilenova.allomancy.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import rhilenova.allomancy.Reference;

public class ItemManager
{
    static Item allomancers_vial;
    static Item mistcloak;

    public static void init()
    {
        allomancers_vial =
            (Item) new ItemAllomancersVial(Reference.ItemIds.allomancers_vial -
                                           Reference.SHIFTED_ID_RANGE_CORRECTION);
        GameRegistry.registerItem(allomancers_vial,
                                  allomancers_vial.getUnlocalizedName());
        
        mistcloak =
                (Item) new ItemMistcloak(Reference.ItemIds.mistcloak -
                                         Reference.SHIFTED_ID_RANGE_CORRECTION);
            GameRegistry.registerItem(mistcloak,
                                      mistcloak.getUnlocalizedName());
    }
}