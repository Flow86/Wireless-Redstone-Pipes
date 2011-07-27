package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.mod_WirelessRedstonePipes;
import net.minecraft.src.ExtraBuildcraftPipes.TileExtractionPipe;
import net.minecraft.src.buildcraft.core.PowerProvider;

/**
 * @author sifldoer
 * 
 */
public class TileWirelessExtractionPipe extends TileExtractionPipe implements IWirelessPipe {
	public TileWirelessExtractionPipe() {
		super();

		PowerProvider powerProvider = mod_WirelessRedstonePipes.wirelessPowerFramework.createPowerProvider();
		powerProvider.configure(50, 1, 1, 1, 64);
		setPowerProvider(powerProvider);
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
