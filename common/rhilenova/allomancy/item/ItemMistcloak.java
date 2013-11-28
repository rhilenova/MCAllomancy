package rhilenova.allomancy.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import rhilenova.allomancy.Allomancy;
import rhilenova.allomancy.model.ModelMistcloak;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMistcloak extends ItemArmor
{
    private ModelBiped model;
    
    public ItemMistcloak(int id)
    {
        super(id, EnumArmorMaterial.CLOTH, 0, 1); // TODO remove magic numbers.
        this.setUnlocalizedName("mistcloak");
        this.setMaxDamage(1024); // TODO balance, move to reference (config?).
        this.setCreativeTab(Allomancy.tabsAllomancy);
        
        model = new ModelMistcloak();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot)
    {
        return model;
    }
}
