package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_WirelessRedstonePipes;
import net.minecraft.src.ExtraBuildcraftPipes.TileExtractionPipe;
import net.minecraft.src.buildcraft.api.ISpecialInventory;
import net.minecraft.src.buildcraft.api.Orientations;
import net.minecraft.src.buildcraft.core.PowerProvider;
import net.minecraft.src.buildcraft.core.Utils;

/**
 * @author sifldoer
 * 
 */
public class TileWirelessExtractionPipe extends TileExtractionPipe implements IWirelessPipe {
	private WirelessFrequencer frequencer;
	
	private static int outputStackSize = 64; // normal 1 or 16
	private static int outputSpeed = 100; // normal 50

	/**
	 * 
	 */
	public TileWirelessExtractionPipe() {
		super();

		PowerProvider powerProvider = mod_WirelessRedstonePipes.wirelessPowerFramework.createPowerProvider();
		powerProvider.configure(outputSpeed, 1, 1, 1, 64);
		setPowerProvider(powerProvider);

		frequencer = new WirelessFrequencer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.WirelessRedstonePipes.IWirelessPipe#getFrequencer()
	 */
	@Override
	public WirelessFrequencer getFrequencer() {
		return frequencer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.WirelessRedstonePipes.IWirelessPipe#getInvName()
	 */
	@Override
	public String getInvName() {
		return "Wireless Extraction Pipe";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#readFromNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		getFrequencer().readFromNBT(nbttagcompound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#writeToNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		getFrequencer().writeToNBT(nbttagcompound);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.WirelessRedstonePipes.IWirelessPipe#canInteractWith
	 * (net.minecraft.src.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return getFrequencer().canInteractWith(this, entityplayer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.ExtraBuildcraftPipes.TileExtractionPipe#doWork()
	 */
	public void doWork() {
		setSourceIfNeeded();
		super.doWork();
	}

	/**
	 * Return the itemstack that can be if something can be extracted from this
	 * inventory, null if none. On certain cases, the extractable slot depends
	 * on the position of the pipe.
	 */
	public ItemStack checkExtract (IInventory inventory, boolean doRemove, Orientations from) {
		if (inventory instanceof ISpecialInventory) {
			return ((ISpecialInventory) inventory).extractItem(doRemove, from);
		}
		
		if (inventory.getSizeInventory() == 2) {
			//  This is an input-output inventory
			
		    int slotIndex = 0;

		    if (from == Orientations.YNeg || from == Orientations.YPos) {
		        slotIndex = 0;
		    } else {
		        slotIndex = 1;
		    }

		    ItemStack slot = inventory.getStackInSlot(slotIndex);

		    if (slot != null && slot.stackSize > 0) {                       
		        if (doRemove) {
		            return inventory.decrStackSize(slotIndex, Math.min(slot.stackSize, outputStackSize));
		        } else {
		            return slot;
		        }                   
		    }       
		} else if (inventory.getSizeInventory() == 3) {
			//  This is a furnace-like inventory
			
			int slotIndex = 0;
			
			if (from == Orientations.YPos) {
				slotIndex = 0;
			} else if (from == Orientations.YNeg) {
				slotIndex = 1;
			} else {
				slotIndex = 2;
			}
			
			ItemStack slot = inventory.getStackInSlot(slotIndex);
			
			if (slot != null && slot.stackSize > 0) {			
				if (doRemove) {
					return inventory.decrStackSize(slotIndex, Math.min(slot.stackSize, outputStackSize));
				} else {
					return slot;
				}			
			}	
		} else {
			// This is a generic inventory
			IInventory inv = Utils.getInventory(inventory);
			
			ItemStack result = checkExtractGeneric(inv, doRemove, from);
			
			if (result != null) {
				return result;
			}	
		}		
		
		return null;
	}
	
	public ItemStack checkExtractGeneric(IInventory inventory,
			boolean doRemove, Orientations from) {
		for (int k = 0; k < inventory.getSizeInventory(); ++k) {
			if (inventory.getStackInSlot(k) != null
					&& inventory.getStackInSlot(k).stackSize > 0) {

				ItemStack slot = inventory.getStackInSlot(k);

				if (slot != null && slot.stackSize > 0) {
					if (doRemove) {
						return inventory.decrStackSize(k, Math.min(slot.stackSize, outputStackSize));
					} else {
						return slot;
					}
				}
			}
		}

		return null;
	}
}
