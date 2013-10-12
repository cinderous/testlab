package cinderous.testlab.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import buildcraft.core.utils.StringUtils;

import cinderous.testlab.ModInfo;
import cinderous.testlab.zeal.TileZealBox;

public class GuiZealBox extends GuiContainer {
	public static final ResourceLocation texture = new ResourceLocation(ModInfo.ID.toLowerCase(), "textures/gui/guizealbox.png");
	private final TileZealBox zealbank;
	
	

	public GuiZealBox(InventoryPlayer invPlayer, TileZealBox tentity) {
		super(new ContainerZealBox(invPlayer, tentity));
		this.zealbank = tentity;
		
		xSize = 176;
		ySize = 165;
		
			
		
	}
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		//String title = "POWER:";
		//fontRenderer.drawString(title, 60, 6, 0x404040);
		
		String dynpower=String.valueOf(zealbank.doGetEnergyStored());
		fontRenderer.drawString(dynpower, 80, 6, 0x404040);

	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	public void updateScreen() {
	 System.out.println("TEST" + zealbank.getEnergyStored());
	}

}
