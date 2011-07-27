package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;

/**
 * @author sifldoer
 * 
 */
public class ContainerWireless extends Container {
	protected IWirelessPipe tile;

	/**
	 * @param iinventory
	 * @param tileentity
	 */
	public ContainerWireless(IInventory iinventory, IWirelessPipe tileentity) {
		tile = tileentity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.Container#isUsableByPlayer(net.minecraft.src.EntityPlayer
	 * )
	 */
	@Override
	public boolean isUsableByPlayer(EntityPlayer entityplayer) {
		return tile.canInteractWith(entityplayer);
	}

}
