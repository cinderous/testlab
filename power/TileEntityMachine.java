package cinderous.testlab.power;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import buildcraft.api.power.PowerHandler;
import buildcraft.core.utils.Utils;

public class TileEntityMachine extends TilePowerBlock {
	public static int MAX_FLUIDAMOUNT = FluidContainerRegistry.BUCKET_VOLUME * 10;
	public static int MAX_WORKTIME = 0;

	// properties
	protected boolean redstoneActivated = false;
	protected int workTime;
	protected ItemStack[] itemStacks;
	protected FluidTank[] fluidTanks;

	// TileEntitySynchronized

	@Override
	protected void readCustomFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readCustomFromNBT(par1NBTTagCompound);

		this.redstoneActivated = par1NBTTagCompound.getBoolean("RedstoneActivated");
		this.workTime = par1NBTTagCompound.getShort("WorkTime");


	}

	@Override
	protected void writeCustomToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeCustomToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setBoolean("RedstoneActivated", this.redstoneActivated);
		par1NBTTagCompound.setShort("WorkTime", (short) this.workTime);

		NBTTagList nbttaglist1 = new NBTTagList();
		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist1.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Slots", nbttaglist1);

		NBTTagList nbttaglist2 = new NBTTagList();
		for (int i = 0; i < this.fluidTanks.length; ++i) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			nbttagcompound1.setByte("Tank", (byte) i);
			this.fluidTanks[i].writeToNBT(nbttagcompound1);
			nbttaglist2.appendTag(nbttagcompound1);
		}
		par1NBTTagCompound.setTag("Tanks", nbttaglist2);
	}

	// TileEntityPowered

	@Override
	public void doWork(PowerHandler workProvider) {
	}

	// ISidedInventory



	// helper


	
	// getter / setter

	public boolean isRedstoneActivated() {
		return this.redstoneActivated;
	}

	public void setRedstoneActivated(boolean redstoneActivated) {
		this.redstoneActivated = redstoneActivated;

		// synchronize with client
		this.onInventoryChanged();
	}

	public int getWorkTime() {
		return this.workTime;
	}

	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}

	public ItemStack[] getItemStacks() {
		return this.itemStacks;
	}

	public void setItemStacks(ItemStack[] itemStacks) {
		this.itemStacks = itemStacks;
	}

	public FluidTank[] getFluidTanks() {
		return this.fluidTanks;
	}

	public void setFluidTanks(FluidTank[] fluidTanks) {
		this.fluidTanks = fluidTanks;
	}
}