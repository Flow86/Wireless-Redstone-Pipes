package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;

public class ContainerWireless extends Container {
	protected IWireless tile;
	
	public ContainerWireless(IInventory iinventory, IWireless tileentity) {
		tile = tileentity;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer entityplayer) {
		return tile.canInteractWith(entityplayer);
	}

}
