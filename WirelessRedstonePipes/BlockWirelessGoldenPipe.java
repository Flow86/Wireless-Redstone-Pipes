package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.buildcraft.api.APIProxy;
import net.minecraft.src.buildcraft.transport.BlockGoldenPipe;

/**
 * @author sifldoer
 * 
 */
public class BlockWirelessGoldenPipe extends BlockGoldenPipe {

	private int inactiveTexture;
	private int activeTexture;

	/**
	 * @param i
	 */
	public BlockWirelessGoldenPipe(int i) {
		super(i);

		inactiveTexture = 1 * 16 + 4;
		activeTexture = 1 * 16 + 14;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.BlockGoldenPipe#getBlockEntity()
	 */
	@Override
	protected TileEntity getBlockEntity() {
		return new TileWirelessGoldenPipe();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.buildcraft.transport.BlockGoldenPipe#getBlockTexture
	 * (net.minecraft.src.IBlockAccess, int, int, int, int)
	 */
	@Override
	public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		if (APIProxy.getWorld() == null) {
			return getBlockTextureFromSideAndMetadata(i, iblockaccess.getBlockMetadata(i, j, k));
		}

		IWirelessPipe tile = (IWirelessPipe) APIProxy.getWorld().getBlockTileEntity(i, j, k);

		return tile.frequencer.isPowered() ? activeTexture : inactiveTexture;
	}

	/*
	 * Block was activated by player.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.Block#blockActivated(net.minecraft.src.World, int,
	 * int, int, net.minecraft.src.EntityPlayer)
	 */
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		if (world.multiplayerWorld)
			return true;

		TileEntity tileentity = world.getBlockTileEntity(i, j, k);

		if (tileentity instanceof IWirelessPipe)
			ModLoader.OpenGUI(entityplayer, new GuiWireless(entityplayer.inventory, (IWirelessPipe) tileentity));

		return true;
	}
}
