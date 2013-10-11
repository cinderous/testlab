package cinderous.testlab.power;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.gates.IAction;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import buildcraft.core.DefaultProps;
import buildcraft.core.IMachine;
import buildcraft.core.network.ISynchronizedTile;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketPayloadArrays;
import buildcraft.core.network.PacketTileUpdate;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.network.TilePacketWrapper;
import buildcraft.core.utils.Utils;
import cinderous.testlab.TestLab;

public class CopyOfTilePowerBlock extends TileEntity implements ISynchronizedTile, IPowerReceptor, IMachine {
	
	  
	@SuppressWarnings("rawtypes")
	private static Map<Class, TilePacketWrapper> updateWrappers = new HashMap<Class, TilePacketWrapper>();
	@SuppressWarnings("rawtypes")
	private static Map<Class, TilePacketWrapper> descriptionWrappers = new HashMap<Class, TilePacketWrapper>();
	private final TilePacketWrapper descriptionPacket;
	private final TilePacketWrapper updatePacket;
	
	  private PowerHandler powerHandler;
	  private boolean init = false;
	
	public CopyOfTilePowerBlock() {
		this.powerHandler = new PowerHandler(this, Type.MACHINE);
	    initPowerProvider();
	    
		if (!updateWrappers.containsKey(this.getClass())) {
			updateWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
		}

		if (!descriptionWrappers.containsKey(this.getClass())) {
			descriptionWrappers.put(this.getClass(), new TilePacketWrapper(this.getClass()));
		}

		updatePacket = updateWrappers.get(this.getClass());
		descriptionPacket = descriptionWrappers.get(this.getClass());
	    
	    }
	
	private void initPowerProvider() {
		this.powerHandler.configure(1, 100, 1, 1000);
		this.powerHandler.configurePowerPerdition(0, 0);
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return this.powerHandler.getPowerReceiver();
	}
	
	@Override
	public void updateEntity() {
		this.powerHandler.update();
		if (!init && !isInvalid()) {
			initialize();
			init = true;
		}

		if (this instanceof IPowerReceptor) {
			IPowerReceptor receptor = ((IPowerReceptor) this);
			receptor.getPowerReceiver(null).update();
		}
	}
	
	public void initialize() {
		Utils.handleBufferedDescription(this);
	}
	
	public void sendNetworkUpdate() {
		if (TestLab.proxy.isSimulating(worldObj)) {
			TestLab.proxy.sendToPlayers(getUpdatePacket(), worldObj, xCoord, yCoord, zCoord, DefaultProps.NETWORK_UPDATE_RANGE);
		}
	}

	
	public PowerHandler getPowerHandler() {
		return this.powerHandler;
	}
	@Override
	public void doWork(PowerHandler workProvider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);


		powerHandler.readFromNBT(data);
		initPowerProvider();
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);

		powerHandler.writeToNBT(data);
	}

	@Override
	public Packet getDescriptionPacket() {
		return new PacketTileUpdate(this).getPacket();
	}

	@Override
	public PacketPayload getPacketPayload() {
		return updatePacket.toPayload(this);
	}

	@Override
	public Packet getUpdatePacket() {
		return new PacketTileUpdate(this).getPacket();
	}

	@Override
	public void handleDescriptionPacket(PacketUpdate packet) throws IOException {
		if (packet.payload instanceof PacketPayloadArrays)
			descriptionPacket.fromPayload(this, (PacketPayloadArrays) packet.payload);
	}

	@Override
	public void handleUpdatePacket(PacketUpdate packet) throws IOException {
		if (packet.payload instanceof PacketPayloadArrays)
			updatePacket.fromPayload(this, (PacketPayloadArrays) packet.payload);
	}

	@Override
	public void postPacketHandling(PacketUpdate packet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean manageFluids() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean manageSolids() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean allowAction(IAction action) {
		// TODO Auto-generated method stub
		return false;
	}
	 	
}
