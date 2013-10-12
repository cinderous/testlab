package cinderous.testlab.crystalline;

import net.minecraft.item.Item;
import cinderous.testlab.lib.Names;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Items {
public static Item itempower;
	
	public static void init() {
		itempower = new ItemPower(600).setTextureName("testlab:itempower");
	}
		
	public static void registerItems() {
		 LanguageRegistry.addName(itempower, Names.itempowerLocal);
	}

}
