package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;

public class ContainerWireless extends Container {
	protected IInventory tile;
	
	public ContainerWireless(IInventory iinventory, IInventory tileentity) {
		tile = tileentity;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer entityplayer) {
		return tile.canInteractWith(entityplayer);
	}

}
