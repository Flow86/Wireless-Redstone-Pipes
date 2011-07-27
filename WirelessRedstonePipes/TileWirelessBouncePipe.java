package net.minecraft.src.WirelessRedstonePipes;

import java.util.LinkedList;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.buildcraft.api.EntityPassiveItem;
import net.minecraft.src.buildcraft.api.Orientations;
import net.minecraft.src.buildcraft.api.Position;
import net.minecraft.src.buildcraft.transport.TilePipe;

/**
 * @author sifldoer
 * 
 */
public class TileWirelessBouncePipe extends TilePipe implements IWirelessPipe {
	public static final int TILEPIPE_META_UNPOWERED = 0;
	public static final int TILEPIPE_META_POWERED = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.TilePipe#getPossibleMovements(
	 * net.minecraft.src.buildcraft.api.Position,
	 * net.minecraft.src.buildcraft.api.EntityPassiveItem)
	 */
	public LinkedList<Orientations> getPossibleMovements(Position position, EntityPassiveItem entitypassiveitem) {
		LinkedList<Orientations> linkedlist;
		if (frequencer.isPowered())
			linkedlist = super.getPossibleMovements(position, entitypassiveitem);
		else {
			linkedlist = new LinkedList<Orientations>();
			linkedlist.add(position.orientation.reverse());
		}

		updatePowerMeta();
		return linkedlist;
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
		boolean newState = frequencer.isPowered();
		boolean oldState = (worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == TILEPIPE_META_POWERED);

		if (oldState != newState) {
			int state = newState ? TILEPIPE_META_POWERED : TILEPIPE_META_UNPOWERED;
			worldObj.setBlockMetadata(xCoord, yCoord, zCoord, state);
			worldObj.markBlockNeedsUpdate(xCoord, yCoord, zCoord);
		}
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
	 * net.minecraft.src.WirelessRedstonePipes.IWireless#canInteractWith(net
	 * .minecraft.src.EntityPlayer)
	 */
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return frequencer.canInteractWith(this, entityplayer);
	}
}
