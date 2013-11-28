package rhilenova.allomancy.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import rhilenova.allomancy.Allomancy;
import rhilenova.allomancy.Reference;

public class ItemAllomancersVial extends Item
{    
	public ItemAllomancersVial(int id)
	{
		super(id);
		this.setUnlocalizedName("emptyVial");
        this.setHasSubtypes(true);
		this.setCreativeTab(Allomancy.tabsAllomancy);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(int id, CreativeTabs tab, List subItems)
    {
	    for (int x = 0; x < 21; ++x)
	    {
	        subItems.add(new ItemStack(id, 1, x));
	    }
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
	    int metal = stack.getItemDamage();
	    String name = Reference.MOD_ID + ":";

	    if (metal >= 0 && metal < Reference.MetalNames.length)
	    {
	        name += Reference.MetalNames[metal];
	    }
	    else
	    {
	        name += "unknown";
	    }
	    
        return "item." + name + "Vial";
    }
}
