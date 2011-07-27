package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.buildcraft.core.PowerFramework;
import net.minecraft.src.buildcraft.core.PowerProvider;

/**
 * @author sifldoer
 * 
 */
public class WirelessPowerFramework extends PowerFramework {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.core.PowerFramework#createPowerProvider()
	 */
	@Override
	public PowerProvider createPowerProvider() {
		return new WirelessPowerProvider();
	}
}
