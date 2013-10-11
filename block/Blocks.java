package cinderous.testlab.block;

import net.minecraft.block.Block;
import cinderous.testlab.lib.Names;
import cinderous.testlab.power.PowerBlock;
import cinderous.testlab.zeal.BlockZealBox;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Blocks {
public static Block mixer;
public static Block power;
public static Block generator;
public static Block powerBlock;
public static Block zealbox;


public static void init() {
mixer = new BlockMixer(501);
power = new BlockPower(502);
generator = new BlockGenerator(503);
powerBlock = new PowerBlock(504);
zealbox = new BlockZealBox(505);
}

public static void registerBlocks() {
GameRegistry.registerBlock(mixer, Names.mixerLocal);
GameRegistry.registerBlock(powerBlock, Names.powerBlockLocal);
GameRegistry.registerBlock(zealbox, Names.zealboxLocal);
//GameRegistry.registerBlock(power, Names.powerLocal);
//GameRegistry.registerBlock(generator, Names.generatorLocal);
}

public static void registerTileEntities() {
GameRegistry.registerTileEntity(cinderous.testlab.power.TilePowerBlock.class, "tilepowerblockLocal");

//ZEALBOX
GameRegistry.registerTileEntity(cinderous.testlab.zeal.TileZealBox.class, "tilezealbox");
}

public static void addNames() {
LanguageRegistry.addName(mixer, Names.mixerLocal);
LanguageRegistry.addName(powerBlock, Names.powerBlockLocal);
LanguageRegistry.addName(zealbox, Names.zealboxLocal);
//LanguageRegistry.addName(power, Names.powerLocal);
//LanguageRegistry.addName(generator, Names.generatorLocal);
}


}