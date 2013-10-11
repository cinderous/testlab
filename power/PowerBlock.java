package cinderous.testlab.power;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cinderous.testlab.ModInfo;
import cinderous.testlab.TestLab;
import cinderous.testlab.lib.Names;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PowerBlock extends Block {

	public PowerBlock(int id) {
		super(id, Material.iron);
		setUnlocalizedName(Names.powerBlockUnlocal);
		setCreativeTab(TestLab.tabTestLab);
		setHardness(5F);
		setResistance(10F);
		}

	@Override
	  public TileEntity createTileEntity(World world, int metadata) {
	    return new TileEntityMachine();
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
		topIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "powerBlock_top");
		sideIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "powerBlock_side");
		frontIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "powerBlock_front");
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
