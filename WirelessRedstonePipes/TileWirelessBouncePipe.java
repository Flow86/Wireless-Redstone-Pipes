package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.buildcraft.api.*;
import net.minecraft.src.ExtraBuildcraftPipes.TileBouncePipe;
import net.minecraft.src.RedstoneEther;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.World;
import net.minecraft.src.ItemStack;
import java.util.LinkedList;

public class TileWirelessBouncePipe extends TileBouncePipe implements IInventory
{
	public TileWirelessBouncePipe()
	{
		super();
		oldFreq = 0;
		currentFreq = 0;
	}

	public Object oldFreq;
	public Object currentFreq;

	public Object getFreq() {
		return currentFreq;
	}
	
	public void setFreq(int freq) {
		if ( freq < 0 )
			freq = 0;
		if ( freq > 9999 ) 
			freq = 9999;
		
		currentFreq = freq;
		updateEntity();
	}
	
	public String getInvName() {
		return "Wireless Bounce Pipe";
	}	
	
	public void updateEntity() {
		// TODO: change freq
		
		updatePowerMeta();
	}
	
	private void updatePowerMeta()
	{
		boolean newState = RedstoneEther.getInstance().getFreqState(currentFreq);
		boolean oldState = (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == TILEPIPE_META_POWERED);
		
		if(oldState != newState)
		{
			int state = newState ? TILEPIPE_META_POWERED : TILEPIPE_META_UNPOWERED;
			worldObj.setBlockMetadata(xCoord, yCoord, zCoord, state);
			worldObj.markBlockNeedsUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	@Override
	public int getSizeInventory() {
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}
		
	@Override
	public ItemStack decrStackSize(int i, int j) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		onInventoryChanged();
	}
		
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}	
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}
		return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}
}
