package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.buildcraft.api.EntityPassiveItem;
import net.minecraft.src.buildcraft.api.Orientations;
import net.minecraft.src.buildcraft.core.Utils;
import net.minecraft.src.buildcraft.transport.TileGoldenPipe;

/**
 * @author sifldoer
 * 
 */
public class TileWirelessGoldenPipe extends TileGoldenPipe implements IWirelessPipe {
	public static final int TILEPIPE_META_UNPOWERED = 0;
	public static final int TILEPIPE_META_POWERED = 1;

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
		boolean newState = frequencer.isPowered();
		boolean oldState = (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == TILEPIPE_META_POWERED);

		if (oldState != newState) {
			worldObj.setBlockMetadata(xCoord, yCoord, zCoord, newState ? TILEPIPE_META_POWERED : TILEPIPE_META_UNPOWERED);
			worldObj.markBlockNeedsUpdate(xCoord, yCoord, zCoord);
		}
	}

	/**
	 * @param item
	 * @param orientation
	 */
	public void entityEntering(EntityPassiveItem item, Orientations orientation) {
		if (frequencer.isPowered())
			item.speed = Utils.pipeNormalSpeed * 20F;

		super.entityEntering(item, orientation);
	}

	@Override
	public String getInvName() {
		return "Wireless Golden Pipe";
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
		frequencer.readFromNBT(nbttagcompound);
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
		frequencer.writeToNBT(nbttagcompound);
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
		return frequencer.canInteractWith(this, entityplayer);
	}
}
