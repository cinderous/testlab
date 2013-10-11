package cinderous.testlab.zeal;

import java.io.IOException;

import buildcraft.core.network.ISynchronizedTile;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketUpdate;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileZeal extends TileEntity implements ISynchronizedTile {

	@Override
	public void handleDescriptionPacket(PacketUpdate packet) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleUpdatePacket(PacketUpdate packet) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postPacketHandling(PacketUpdate packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Packet getUpdatePacket() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PacketPayload getPacketPayload() {
		// TODO Auto-generated method stub
		return null;
	}

}
