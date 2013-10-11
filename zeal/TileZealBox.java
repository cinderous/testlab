package cinderous.testlab.zeal;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.gates.IAction;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import buildcraft.core.IMachine;

public class TileZealBox extends TileZeal implements IPowerReceptor, IMachine, IInventory {
	
	private ItemStack[] inventory;
	private PowerHandler powerHandler;
	private float lastSyncPowerStored;
	private float storedEnergy;

	public TileZealBox() {
		inventory = new ItemStack[9];
		powerHandler = new PowerHandler(this, Type.STORAGE);
		initPowerProvider();
	}

	private void initPowerProvider() {
		powerHandler.configure(50, 150, 25, 1000);
		powerHandler.configurePowerPerdition(1, 1);
	}
	
	 @Override
	  public void updateEntity() {

	    if(worldObj == null) { // sanity check
	      return;
	    }

	    if(worldObj.isRemote) {
	      return;
	    } // else is server, do all logic only on the server

	    // do the required tick to keep BC API happy
	    float stored = powerHandler.getEnergyStored();
	    powerHandler.update();
	    // do a dummy recieve of power to force the updating of what is an isn't a
	    // power source as we rely on this
	    // to make sure we dont both send and recieve to the same source
	    powerHandler.getPowerReceiver().receiveEnergy(Type.STORAGE, 1, null);
	    powerHandler.setEnergy(stored);

	    boolean requiresClientSync = false;
	    storedEnergy = powerHandler.getEnergyStored();

	    // Update if our power has changed by more than 0.5%
	    requiresClientSync |= lastSyncPowerStored != storedEnergy && worldObj.getTotalWorldTime() % 21 == 0;

	    if(requiresClientSync) {
	      lastSyncPowerStored = storedEnergy;

	      // this will cause 'getPacketDescription()' to be called and its result
	      // will be sent to the PacketHandler on the other end of
	      // client/server connection
	      worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	      onInventoryChanged();
	    }

	  }
	
	 public float doGetEnergyStored() {
		    return storedEnergy;
		  }
	
	@Override
	public boolean isActive() {
		// TODO Auto-generated method stub
		return false;
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

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return powerHandler.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return worldObj;
	}

	//IInvetory
	
	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int count) {
		ItemStack itemstack = getStackInSlot(slot);
	
		if(itemstack != null) {
		if(itemstack.stackSize <= count) {
			setInventorySlotContents(slot, null);
			} else {
			itemstack = itemstack.splitStack(count);
			onInventoryChanged();
			}
		}
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
	ItemStack itemstack = getStackInSlot(slot);
	setInventorySlotContents(slot, null);
	return itemstack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
	inventory[slot] = itemstack;

	if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
	itemstack.stackSize = getInventoryStackLimit();
	}
	onInventoryChanged();
	}

	@Override
	public String getInvName() {
		return "tilezealbox";
	}

	@Override
	public boolean isInvNameLocalized() {
		return true;
	}

	@Override
	public int getInventoryStackLimit() {
		return 2;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
	return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeChest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}

	//saving
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
	  super.writeToNBT(compound);

	  NBTTagList list = new NBTTagList();

	  for(int i = 0; i < getSizeInventory(); i++) {
	        ItemStack itemstack = getStackInSlot(i);

	        if(itemstack != null) {
	          NBTTagCompound item = new NBTTagCompound();

	          item.setByte("SlotZealBox", (byte) i);
	          itemstack.writeToNBT(item);
	          list.appendTag(item);
	        }
	  }

	  compound.setTag("ItemsZealBox", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
	  super.readFromNBT(compound);

	  NBTTagList list = compound.getTagList("ItemsZealBox");

	  for(int i = 0; i < list.tagCount(); i++) {
	        NBTTagCompound item = (NBTTagCompound) list.tagAt(i);
	        int slot = item.getByte("SlotZealBox");

	        if(slot >= 0 && slot < getSizeInventory()) {
	          setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(item));
	        }
	  }
	}
	
}
