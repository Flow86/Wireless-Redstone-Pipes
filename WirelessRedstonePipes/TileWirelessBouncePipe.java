package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.buildcraft.api.*;
import net.minecraft.src.buildcraft.transport.TilePipe;
import net.minecraft.src.*;
import java.util.LinkedList;

/**
 * @author sifldoer
 * 
 */
public class TileWirelessBouncePipe extends TilePipe implements IWirelessPipe {
	public static final int TILEPIPE_META_UNPOWERED = 0;
	public static final int TILEPIPE_META_POWERED = 1;

	public Object oldFreq;
	public Object currentFreq;

	/**
	 * 
	 */
	public TileWirelessBouncePipe() {
		super();
		oldFreq = 0;
		currentFreq = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#getPossibleMovements(
	 * net.minecraft.src.buildcraft.api.Position,
	 * net.minecraft.src.buildcraft.api.EntityPassiveItem)
	 */
	public LinkedList<Orientations> getPossibleMovements(Position position,
			EntityPassiveItem entitypassiveitem) {
		LinkedList<Orientations> linkedlist;
		if (isPowered())
			linkedlist = super
					.getPossibleMovements(position, entitypassiveitem);
		else {
			linkedlist = new LinkedList<Orientations>();
			linkedlist.add(position.orientation.reverse());
		}

		updatePowerMeta();
		return linkedlist;
	}

	/**
	 * @return
	 */
	public boolean isPowered() {
		return (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == TILEPIPE_META_POWERED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.buildcraft.transport.TilePipe#updateEntity()
	 */
	public void updateEntity() {
		super.updateEntity();
		updatePowerMeta();
	}

	/**
	 * 
	 */
	private void updatePowerMeta() {
		boolean newState = RedstoneEther.getInstance()
				.getFreqState(currentFreq);
		boolean oldState = isPowered();

		if (oldState != newState) {
			int state = newState ? TILEPIPE_META_POWERED
					: TILEPIPE_META_UNPOWERED;
			worldObj.setBlockMetadata(xCoord, yCoord, zCoord, state);
			worldObj.markBlockNeedsUpdate(xCoord, yCoord, zCoord);
		}
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
		super.writeToNBT(nbttagcompound);

		NBTTagList nbttaglist3 = new NBTTagList();
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setString("freq", currentFreq.toString());
		nbttaglist3.setTag(nbttagcompound1);
		nbttagcompound.setTag("Frequency", nbttaglist3);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.WirelessRedstonePipes.IWireless#getFreq()
	 */
	@Override
	public Object getFreq() {
		return currentFreq;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.WirelessRedstonePipes.IWireless#setFreq(int)
	 */
	@Override
	public void setFreq(int frequency) {
		if (frequency < 0)
			frequency = 0;
		if (frequency > 9999)
			frequency = 9999;

		currentFreq = frequency;
		updateEntity();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.WirelessRedstonePipes.IWireless#getInvName()
	 */
	@Override
	public String getInvName() {
		return "Wireless Bounce Pipe";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.WirelessRedstonePipes.IWireless#canInteractWith(net
	 * .minecraft.src.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		}
		return entityplayer.getDistanceSq((double) xCoord + 0.5D,
				(double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64D;
	}
}
