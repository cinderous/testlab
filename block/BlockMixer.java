package cinderous.testlab.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cinderous.testlab.ModInfo;
import cinderous.testlab.TestLab;
import cinderous.testlab.lib.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMixer extends Block {

	public BlockMixer(int id) {
		super(id, Material.iron);
		setUnlocalizedName(Names.mixerUnlocal);
		setCreativeTab(TestLab.tabTestLab);
		setHardness(5F);
		setResistance(10F);
		}

	@SideOnly(Side.CLIENT)
	public static Icon topIcon;
	@SideOnly(Side.CLIENT)
	public static Icon sideIcon;
	@SideOnly(Side.CLIENT)
	public static Icon frontIcon;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon) {
		topIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "mixer_top");
		sideIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "mixer_side");
		frontIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "mixer_front");
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		if(side == 0 || side == 1) {
		return topIcon;
		} else if(side == 2) {
		return frontIcon;
		} else {
		return sideIcon;
		}
	}
}

