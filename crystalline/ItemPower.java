package cinderous.testlab.crystalline;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cinderous.testlab.TestLab;
import cinderous.testlab.lib.Names;

public class ItemPower extends Item {

	public ItemPower(int id) {
        super(id);
        
        // Constructor Configuration
        maxStackSize = 1;
        setCreativeTab(TestLab.tabTestLab);
        setUnlocalizedName(Names.itempowerUnlocal);
	}
	
}
