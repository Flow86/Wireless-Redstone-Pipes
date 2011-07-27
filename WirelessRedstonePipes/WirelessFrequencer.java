package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.RedstoneEther;
import net.minecraft.src.TileEntity;

/**
 * @author sifldoer
 * 
 */
public class WirelessFrequencer {
	public Object currentFreq;

	/**
	 * 
	 */
	public WirelessFrequencer() {
		currentFreq = 0;
	}

	/**
	 * @return
	 */
	public Object getFreq() {
		return currentFreq;
	}

	/**
	 * @param frequency
	 */
	public void setFreq(IWirelessPipe tile, int frequency) {
		if (frequency < 0)
			frequency = 0;
		if (frequency > 9999)
			frequency = 9999;

		currentFreq = frequency;
		tile.updateEntity();
	}

	/**
	 * @return
	 */
	public boolean isPowered() {
		return RedstoneEther.getInstance().getFreqState(currentFreq);
	}

	/**
	 * @param entityplayer
	 * @return
	 */
	public boolean canInteractWith(TileEntity tile, EntityPlayer entityplayer) {
		if (tile.worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord) != tile)
			return false;

		return entityplayer.getDistanceSq((double) tile.xCoord + 0.5D, (double) tile.yCoord + 0.5D, (double) tile.zCoord + 0.5D) <= 64D;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#readFromNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist3 = nbttagcompound.getTagList("Frequency");
		NBTTagCompound nbttagcompound3 = (NBTTagCompound) nbttaglist3.tagAt(0);
		try {
			String freq = nbttagcompound3.getString("freq");
			try {
				currentFreq = Integer.parseInt(freq);
			} catch (NumberFormatException e) {
				currentFreq = freq;
			}
		} catch (ClassCastException e) {
			currentFreq = nbttagcompound3.getInteger("freq");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#writeToNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist3 = new NBTTagList();
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setString("freq", currentFreq.toString());
		nbttaglist3.setTag(nbttagcompound1);
		nbttagcompound.setTag("Frequency", nbttaglist3);
	}
}
