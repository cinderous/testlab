package cinderous.testlab.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import cinderous.testlab.ModInfo;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandlerCore implements IPacketHandler {
	
	 public static final String CHANNEL = ModInfo.CHANNEL;
	
	    @Override
    public void onPacketData(INetworkManager manager,
                    Packet250CustomPayload packet, Player playerEntity) {
    	
    }
	    
    public static Packet energyPacket(TileEntity tentity) {
		return createEnergyPacket(CHANNEL, tentity);
		
    }

	private static Packet createEnergyPacket(String channel2, TileEntity tentity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
	    DataOutputStream dos = new DataOutputStream(bos);
	    try {
	      dos.writeInt(tentity.xCoord);
	      dos.writeInt(tentity.yCoord);
	      dos.writeInt(tentity.zCoord);
	     // NBTTagCompound root = new NBTTagCompound();
	     // tentity.writeToNBT(root);
	     // writeNBTTagCompound(root, dos);

	    } catch (IOException e) {
	      //
	    }

	    Packet250CustomPayload pkt = new Packet250CustomPayload();
	    pkt.channel = channel2;
	    pkt.data = bos.toByteArray();
	    pkt.length = bos.size();
	    pkt.isChunkDataPacket = true;
	    return pkt;
	}
    	
    
}