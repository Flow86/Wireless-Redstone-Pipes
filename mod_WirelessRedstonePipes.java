package net.minecraft.src;

import java.io.File;

import net.minecraft.src.WirelessRedstonePipes.BlockWirelessBouncePipe;
import net.minecraft.src.WirelessRedstonePipes.BlockWirelessExtractionPipe;
import net.minecraft.src.WirelessRedstonePipes.BlockWirelessGoldenPipe;
import net.minecraft.src.WirelessRedstonePipes.TileWirelessBouncePipe;
import net.minecraft.src.WirelessRedstonePipes.TileWirelessExtractionPipe;
import net.minecraft.src.WirelessRedstonePipes.TileWirelessGoldenPipe;
import net.minecraft.src.WirelessRedstonePipes.WirelessPowerFramework;
import net.minecraft.src.buildcraft.core.Configuration;
import net.minecraft.src.buildcraft.core.CoreProxy;

/**
 * @author sifldoer
 * 
 */
public class mod_WirelessRedstonePipes extends BaseModMp {

	public static mod_WirelessRedstonePipes instance;
	private Configuration config;

	public static BlockWirelessBouncePipe wirelessBouncePipeBlock;
	public static BlockWirelessExtractionPipe wirelessExtractionPipeBlock;
	public static BlockWirelessGoldenPipe wirelessGoldenPipeBlock;
	public static WirelessPowerFramework wirelessPowerFramework;

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

		wirelessPowerFramework = new WirelessPowerFramework();

		this.config = new Configuration(new File(CoreProxy.getBuildCraftBase(),
				"config/wirelessredstonepipes.cfg"), false);

		Configuration.Property wirelessBouncePipeBlockId = this.config
				.getOrCreateBlockIdProperty("wirelessBouncePipe", 160);
		Configuration.Property wirelessExtractionPipeBlockId = this.config
				.getOrCreateBlockIdProperty("wirelessExtractionPipe", 161);
		Configuration.Property wirelessGoldenPipeBlockId = this.config
				.getOrCreateBlockIdProperty("wirelessGoldenPipe", 162);

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

		if (Integer.parseInt(wirelessExtractionPipeBlockId.value) != 0) {
			wirelessExtractionPipeBlock = new BlockWirelessExtractionPipe(
					Integer.parseInt(wirelessExtractionPipeBlockId.value));
			CoreProxy.addName(wirelessExtractionPipeBlock
					.setBlockName("wirelessExtractionPipe"),
					"Wireless Extraction Pipe");

			ModLoader.RegisterBlock(wirelessExtractionPipeBlock);

			ModLoader.AddRecipe(new ItemStack(wirelessExtractionPipeBlock, 8),
					new Object[] { " R ", "WGW", Character.valueOf('W'),
							Block.planks, Character.valueOf('G'), Block.glass,
							Character.valueOf('R'),
							mod_WirelessRedstone.blockWirelessR });

			ModLoader.RegisterTileEntity(TileWirelessExtractionPipe.class,
					"WirelessExtractionPipe");

		}

		if (Integer.parseInt(wirelessGoldenPipeBlockId.value) != 0) {
			wirelessGoldenPipeBlock = new BlockWirelessGoldenPipe(
					Integer.parseInt(wirelessGoldenPipeBlockId.value));
			CoreProxy.addName(
					wirelessGoldenPipeBlock.setBlockName("wirelessGoldenPipe"),
					"Wireless Golden Pipe");

			ModLoader.RegisterBlock(wirelessGoldenPipeBlock);

			ModLoader.AddRecipe(new ItemStack(wirelessGoldenPipeBlock, 8),
					new Object[] { "PGP", Character.valueOf('P'),
							Item.ingotGold, Character.valueOf('G'),
							mod_WirelessRedstone.blockWirelessR });

			ModLoader.RegisterTileEntity(TileWirelessGoldenPipe.class,
					"WirelessGoldenPipe");

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.BaseMod#Version()
	 */
	@Override
	public String Version() {
		return "1.7.3.1";
	}
}
