package cinderous.testlab.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cinderous.testlab.TestLab;
import cinderous.testlab.client.ContainerZealBox;
import cinderous.testlab.client.GuiZealBox;
import cinderous.testlab.zeal.TileZealBox;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(TestLab.instance, this);
		}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity entity = world.getBlockTileEntity(x, y, z);

	switch(id) {
	case 0:
	if(entity != null && entity instanceof TileZealBox) {
	return new ContainerZealBox(player.inventory, (TileZealBox) entity);
	} else {
	return null;
	}
	default:
	return null;
	}
	}


	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity entity = world.getBlockTileEntity(x, y, z);

	switch(id) {
	case 0:
	if(entity != null && entity instanceof TileZealBox) {
	return new GuiZealBox(player.inventory, (TileZealBox) entity);
	} else {
	return null;
	}
	default:
	return null;
	}
	}
	
}
