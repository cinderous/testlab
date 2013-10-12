package cinderous.testlab.crystalline;

import cinderous.testlab.TestLab;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import buildcraft.core.CreativeTabBuildCraft;
import buildcraft.core.utils.StringUtils;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCrystalline extends Item {

	private String iconName;
	public ItemCrystalline(int i) {
		super(i);
		setCreativeTab(TestLab.tabTestLab);
	}
	
	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return StringUtils.localize(getUnlocalizedName(itemstack));
	}

	@Override
	public Item setUnlocalizedName(String par1Str) {
		iconName = par1Str;
		return super.setUnlocalizedName(par1Str);
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("testlab:" + iconName);
    }

}
