package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;

public interface IWireless
{
	public abstract String getInvName();

	public abstract Object getFreq();
	public abstract void setFreq(int frequency);

	public abstract boolean canInteractWith(EntityPlayer entityplayer);
}
