package net.minecraft.src.WirelessRedstonePipes;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.IInventory;

import org.lwjgl.opengl.GL11;

/**
 * @author sifldoer
 * 
 */
public class GuiWireless extends GuiContainer {
	protected IWirelessPipe tile;

	/**
	 * @param inventory
	 * @param tileentity
	 */
	public GuiWireless(IInventory inventory, IWirelessPipe tileentity) {
		super(new ContainerWireless(inventory, tileentity));
		tile = tileentity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.GuiContainer#initGui()
	 */
	@SuppressWarnings("unchecked")
	public void initGui() {
		controlList.add(new GuiButton(0, (width / 2) + 10, (height / 2) - 20, 20, 20, "+"));
		controlList.add(new GuiButton(1, (width / 2) - 30, (height / 2) - 20, 20, 20, "-"));
		controlList.add(new GuiButton(2, (width / 2) + 32, (height / 2) - 20, 20, 20, "+10"));
		controlList.add(new GuiButton(3, (width / 2) - 52, (height / 2) - 20, 20, 20, "-10"));
		controlList.add(new GuiButton(4, (width / 2) + 54, (height / 2) - 20, 26, 20, "+100"));
		controlList.add(new GuiButton(5, (width / 2) - 80, (height / 2) - 20, 26, 20, "-100"));
		controlList.add(new GuiButton(6, (width / 2) + 48, (height / 2) - 42, 32, 20, "+1000"));
		controlList.add(new GuiButton(7, (width / 2) - 80, (height / 2) - 42, 32, 20, "-1000"));
		super.initGui();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.GuiContainer#drawScreen(int, int, float)
	 */
	public void drawScreen(int i, int j, float f) {
		super.drawScreen(i, j, f);

		GL11.glDisable(2896 /* GL_LIGHTING */);
		drawStringBorder((width / 2) - (fontRenderer.getStringWidth(tile.getFrequencer().getFreq() + "") / 2), (height / 2) - 35,
				(width / 2) + (fontRenderer.getStringWidth(tile.getFrequencer().getFreq() + "") / 2));

		fontRenderer.drawString(tile.getFrequencer().getFreq() + "", (width / 2) - (fontRenderer.getStringWidth(tile.getFrequencer().getFreq() + "") / 2),
				(height / 2) - 35, 0x404040);
		GL11.glEnable(2896 /* GL_LIGHTING */);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.GuiScreen#actionPerformed(net.minecraft.src.GuiButton)
	 */
	protected void actionPerformed(GuiButton guibutton) {
		Object a = tile.getFrequencer().getFreq();
		Object b = tile.getFrequencer().getFreq();
		int freq, oldFreq;
		try {
			freq = Integer.parseInt(a.toString());
			oldFreq = Integer.parseInt(b.toString());
		} catch (NumberFormatException e) {
			return;
		}

		switch (guibutton.id) {
		case 0:
			freq++;
			break;
		case 1:
			freq--;
			break;
		case 2:
			freq += 10;
			break;
		case 3:
			freq -= 10;
			break;
		case 4:
			freq += 100;
			break;
		case 5:
			freq -= 100;
			break;
		case 6:
			freq += 1000;
			break;
		case 7:
			freq -= 1000;
			break;
		}
		if (freq > 9999)
			freq -= 10000;
		if (freq < 0)
			freq += 10000;

		if (oldFreq != freq)
			tile.getFrequencer().setFreq(tile, freq);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 */
	protected void drawStringBorder(int x1, int y1, int x2) {
		drawRect(x1 - 3, y1 - 3, x2 + 3, y1 + 10, 0xff000000);
		drawRect(x1 - 2, y1 - 2, x2 + 2, y1 + 9, 0xffffffff);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.src.GuiContainer#drawGuiContainerForegroundLayer()
	 */
	protected void drawGuiContainerForegroundLayer() {
		drawStringBorder((xSize / 2) - (fontRenderer.getStringWidth(tile.getInvName()) / 2), 6, (xSize / 2)
				+ (fontRenderer.getStringWidth(tile.getInvName()) / 2));
		fontRenderer.drawString(tile.getInvName(), (xSize / 2) - (fontRenderer.getStringWidth(tile.getInvName()) / 2), 6, 0x404040);

		drawStringBorder((xSize / 2) - (fontRenderer.getStringWidth("Frequency") / 2), 32, (xSize / 2) + (fontRenderer.getStringWidth("Frequency") / 2));
		fontRenderer.drawString("Frequency", (xSize / 2) - (fontRenderer.getStringWidth("Frequency") / 2), 32, 0x404040);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.src.GuiContainer#drawGuiContainerBackgroundLayer(float)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float f) {
		int i = mc.renderEngine.getTexture("/net/minecraft/src/WirelessRedstonePipes/gui/wifi_small.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(i);
		int j = (width - xSize) / 2;
		int k = (height - ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, xSize, ySize);
	}
}
