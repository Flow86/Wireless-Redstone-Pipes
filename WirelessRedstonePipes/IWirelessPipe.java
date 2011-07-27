package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;

/**
 * @author sifldoer
 * 
 */
public interface IWirelessPipe {
	public abstract String getInvName();

	public abstract Object getFreq();

	public abstract void setFreq(int frequency);
	
	public abstract boolean isPowered();

	public abstract boolean canInteractWith(EntityPlayer entityplayer);
}
