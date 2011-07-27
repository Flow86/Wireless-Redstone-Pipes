package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;

/**
 * @author sifldoer
 * 
 */
public interface IWirelessPipe {
	public WirelessFrequencer frequencer = new WirelessFrequencer();

	public abstract String getInvName();

	public abstract boolean canInteractWith(EntityPlayer entityplayer);

	public abstract void readFromNBT(NBTTagCompound nbttagcompound);

	public abstract void writeToNBT(NBTTagCompound nbttagcompound);

	public abstract void updateEntity();
}
