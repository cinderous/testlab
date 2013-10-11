package cinderous.testlab.power;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import buildcraft.BuildCraftCore;
import buildcraft.api.core.SafeTimeTracker;
import buildcraft.api.gates.IAction;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import buildcraft.api.recipes.RefineryRecipes;
import buildcraft.api.recipes.RefineryRecipes.Recipe;
import buildcraft.core.DefaultProps;
import buildcraft.core.IMachine;
import buildcraft.core.fluids.SingleUseTank;
import buildcraft.core.fluids.TankManager;
import buildcraft.core.network.ISynchronizedTile;
import buildcraft.core.network.PacketPayload;
import buildcraft.core.network.PacketPayloadArrays;
import buildcraft.core.network.PacketPayloadStream;
import buildcraft.core.network.PacketTileUpdate;
import buildcraft.core.network.PacketUpdate;
import buildcraft.core.network.TilePacketWrapper;
import buildcraft.core.proxy.CoreProxy;
import buildcraft.core.utils.Utils;
import cinderous.testlab.TestLab;

public class TilePowerBlock extends TileEntity implements ISynchronizedTile, IPowerReceptor {
	
	private PowerHandler powerHandler;
	private float[] recentEnergy = new float[20];
	private float recentEnergyAverage;
	private float currentInput; // TODO
	private int tick = 0;

	public TilePowerBlock() {
		this.powerHandler = new PowerHandler(this, Type.MACHINE);
		this.powerHandler.configure(2, 100, 1, 10000);
		this.powerHandler.configurePowerPerdition(0, 0);
	}

	// TileEntitySynchronized

	protected void readCustomFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.powerHandler.readFromNBT(par1NBTTagCompound);
	}

	protected void writeCustomToNBT(NBTTagCompound par1NBTTagCompound) {
		this.powerHandler.writeToNBT(par1NBTTagCompound);
	}

	// IPowerReceptor

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return this.powerHandler.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}

	public float calculateRecentEnergyAverage() {
		float recentEnergyAverage = 0;
		for (int i = 0; i < this.recentEnergy.length; i++) {
			recentEnergyAverage += this.recentEnergy[i] / (this.recentEnergy.length - 1);
		}
		return recentEnergyAverage;
	}

	@Override
	public void updateEntity() {
		if (this.worldObj.isRemote) {
			return;
		}

		this.tick++;
		this.tick = this.tick % this.recentEnergy.length;
		this.recentEnergy[this.tick] = 0.0f;

		this.powerHandler.update();
	}

	// getter / setter

	public PowerHandler getPowerHandler() {
		return this.powerHandler;
	}

	public void setPowerHandler(PowerHandler powerHandler) {
		this.powerHandler = powerHandler;
	}

	public float getRecentEnergyAverage() {
		return this.recentEnergyAverage;
	}

	public void setRecentEnergyAverage(float recentEnergyAverage) {
		this.recentEnergyAverage = recentEnergyAverage;
	}

	public float getCurrentEnergy() {
		return this.recentEnergy[this.tick];
	}

	public void setCurrentEnergy(float currentEnergy) {
		this.recentEnergy[this.tick] = currentEnergy;
	}

	public float getCurrentInput() {
		return this.currentInput;
	}

	public void setCurrentInput(float currentInput) {
		this.currentInput = currentInput;
	}

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