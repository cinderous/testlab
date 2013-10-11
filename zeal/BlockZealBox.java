package cinderous.testlab.zeal;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cinderous.testlab.ModInfo;
import cinderous.testlab.TestLab;
import cinderous.testlab.lib.Names;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZealBox extends BlockContainer {

	public BlockZealBox(int i) {
		super(i, Material.iron);
		setUnlocalizedName(Names.zealboxUnlocal);
		setHardness(5F);
		setCreativeTab(TestLab.tabTestLab);
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileZealBox();
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
		topIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "zealbox_top");
		sideIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "zealbox_side");
		frontIcon = icon.registerIcon(ModInfo.ID.toLowerCase() + ":" + "zealbox_front");
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
	
	//
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(!world.isRemote) {
			FMLNetworkHandler.openGui(player, TestLab.instance, 0, world, x, y, z);
		}
		return true;
	}
	
	
}
