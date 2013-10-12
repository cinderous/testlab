package cinderous.testlab.proxy;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy {
	
public void initRenderers() {

}

public void initSounds() {

}

public boolean isSimulating(World world) {
	return !world.isRemote;
}

public void sendToPlayers(Packet packet, World world, int x, int y, int z, int maxDistance) {
	if (packet != null) {
		for (int j = 0; j < world.playerEntities.size(); j++) {
			EntityPlayerMP player = (EntityPlayerMP) world.playerEntities.get(j);

			if (Math.abs(player.posX - x) <= maxDistance && Math.abs(player.posY - y) <= maxDistance && Math.abs(player.posZ - z) <= maxDistance) {
				player.playerNetServerHandler.sendPacketToPlayer(packet);
			}
		}
	}
}

public void registerServerTickHandler()
{
 TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER);
}

}