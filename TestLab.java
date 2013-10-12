package cinderous.testlab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cinderous.testlab.block.Blocks;
import cinderous.testlab.crystalline.Items;
import cinderous.testlab.network.GuiHandler;
import cinderous.testlab.network.PacketHandlerCore;
import cinderous.testlab.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {ModInfo.CHANNEL}, packetHandler = PacketHandlerCore.class)

public class TestLab {
	@Instance(ModInfo.ID)
	public static TestLab instance;
	
	
	
@SidedProxy(clientSide = ModInfo.PROXY + "ClientProxy", serverSide = ModInfo.PROXY + "CommonProxy")
public static CommonProxy proxy;


public static CreativeTabs tabTestLab = new CreativeTabs("tabTestLab") {
	public ItemStack getIconItemStack() {
        return new ItemStack(Item.cauldron, 1, 0);
    }
};


@EventHandler
public static void preInit(FMLPreInitializationEvent event) {

}

@EventHandler
public static void init(FMLInitializationEvent event) {
proxy.initRenderers();
proxy.initSounds();
proxy.registerServerTickHandler();

LanguageRegistry.instance().addStringLocalization("itemGroup.tabCustom", "en_US", "My Custom Tab");

new GuiHandler();

Blocks.init();
Blocks.registerBlocks();
Blocks.addNames();
Blocks.registerTileEntities();
Items.init();
Items.registerItems();
}

@EventHandler
public static void postInit(FMLPostInitializationEvent event) {

}
}
