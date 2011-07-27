package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.ExtraBuildcraftPipes.BlockExtractionPipe;

/**
 * @author sifldoer
 *
 */
public class BlockWirelessExtractionPipe extends BlockExtractionPipe
{

    /**
     * @param i
     */
    public BlockWirelessExtractionPipe(int i)
    {
        super(i);
    }

    /* (non-Javadoc)
     * @see net.minecraft.src.ExtraBuildcraftPipes.BlockExtractionPipe#getBlockEntity()
     */
    protected TileEntity getBlockEntity()
    {
        return new TileWirelessExtractionPipe();
    }

	/*
	 * Block was activated by player.
	 * 
	 * (non-Javadoc)
	 * @see net.minecraft.src.Block#blockActivated(net.minecraft.src.World, int,
	 * int, int, net.minecraft.src.EntityPlayer)
	 */
	public boolean blockActivated(World world, int i, int j, int k,
			EntityPlayer entityplayer) {
		if (world.multiplayerWorld)
			return true;

		TileEntity tileentity = world.getBlockTileEntity(i, j, k);

		if (tileentity instanceof IWirelessPipe)
			ModLoader.OpenGUI(entityplayer,
					new GuiWireless(entityplayer.inventory,
							(IWirelessPipe) tileentity));

		return true;
	}
}
