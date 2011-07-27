package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.ExtraBuildcraftPipes.BlockBouncePipe;
import net.minecraft.src.*;

public class BlockWirelessBouncePipe extends BlockBouncePipe
{
	public BlockWirelessBouncePipe(int i)
	{
		super(i);
	}

	protected TileEntity getBlockEntity()
	{
		return new TileWirelessBouncePipe();
	}

	// Block was activated by player.
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		if ( world.multiplayerWorld ) return true;
		
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);

		if ( tileentity instanceof TileWirelessBouncePipe ) 
			ModLoader.OpenGUI(entityplayer, new GuiWireless(entityplayer.inventory, (TileWirelessBouncePipe)tileentity));
		
		return true;
	}
}
