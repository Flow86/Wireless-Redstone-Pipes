package net.minecraft.src;

import net.minecraft.src.WirelessRedstonePipes.BlockWirelessBouncePipe;
import net.minecraft.src.WirelessRedstonePipes.TileWirelessBouncePipe;
import net.minecraft.src.buildcraft.core.Configuration;
import net.minecraft.src.buildcraft.core.Configuration.Property;
import net.minecraft.src.buildcraft.core.CoreProxy;
import net.minecraft.src.BlockRedstoneWirelessR;
import java.io.File;

/**
 * @author sifldoer
 * 
 */
public class mod_WirelessRedstonePipes extends BaseModMp {

	public static mod_WirelessRedstonePipes instance;
	private Configuration config;

	public static BlockWirelessBouncePipe wirelessBouncePipeBlock;

	/**
	 * 
	 */
	public mod_WirelessRedstonePipes() {
		instance = this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.BaseModMp#ModsLoaded()
	 */
	public void ModsLoaded() {
		super.ModsLoaded();

		mod_BuildCraftCore.initialize();

		this.config = new Configuration(new File(CoreProxy.getBuildCraftBase(),
				"config/wirelessredstonepipes.cfg"), false);

		Configuration.Property wirelessBouncePipeBlockId = this.config
				.getOrCreateBlockIdProperty("wirelessBouncePipe", 160);

		this.config.save();

		if (Integer.parseInt(wirelessBouncePipeBlockId.value) != 0) {
			wirelessBouncePipeBlock = new BlockWirelessBouncePipe(
					Integer.parseInt(wirelessBouncePipeBlockId.value));
			CoreProxy.addName(
					wirelessBouncePipeBlock.setBlockName("wirelessBouncePipe"),
					"Wireless Bounce Pipe");
			
			ModLoader.RegisterBlock(wirelessBouncePipeBlock);
			
			ModLoader.AddRecipe(new ItemStack(wirelessBouncePipeBlock, 8),
					new Object[] { "CSC", Character.valueOf('C'),
							Block.cobblestone, Character.valueOf('S'),
							mod_WirelessRedstone.blockWirelessR });

			ModLoader.RegisterTileEntity(TileWirelessBouncePipe.class,
					"WirelessBouncePipe");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.BaseMod#Version()
	 */
	@Override
	public String Version() {
		return "1.7.3.0";
	}
}
