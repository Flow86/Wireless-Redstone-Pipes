package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.BuildCraftCore;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.buildcraft.core.IPowerReceptor;
import net.minecraft.src.buildcraft.core.PowerProvider;

/**
 * @author sifldoer
 * 
 */
public class WirelessPowerProvider extends PowerProvider {

	private boolean lastPower = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.core.PowerProvider#update(net.minecraft.
	 * src.buildcraft.core.IPowerReceptor)
	 */
	@Override
	public void update(IPowerReceptor receptor) {
		IWirelessPipe tile = (IWirelessPipe) receptor;

		boolean currentPower = tile.getFrequencer().isPowered();

		if (BuildCraftCore.continuousCurrentModel) {
			if (currentPower) {
				workIfCondition(receptor);
			}
		} else {
			if (currentPower != lastPower) {
				lastPower = currentPower;

				if (currentPower) {
					workIfCondition(receptor);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.buildcraft.core.PowerProvider#useEnergy(int, int)
	 */
	public int useEnergy(int min, int max) {
		return min;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.core.PowerProvider#readFromNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		lastPower = nbttagcompound.getBoolean("lastPower");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.core.PowerProvider#writeToNBT(net.minecraft
	 * .src.NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		nbttagcompound.setBoolean("lastPower", lastPower);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.buildcraft.core.PowerProvider#configure(int, int,
	 * int, int, int)
	 */
	public void configure(int latency, int minEnergyReceived, int maxEnergyReceived, int minActivationEnergy, int maxStoredEnergy) {
		super.configure(latency, minEnergyReceived, maxEnergyReceived, minActivationEnergy, maxStoredEnergy);

		this.minActivationEnergy = 0;
	}
}
