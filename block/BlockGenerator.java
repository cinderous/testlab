package cinderous.testlab.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cinderous.testlab.TestLab;
import cinderous.testlab.lib.Names;

public class BlockGenerator extends Block {

	public BlockGenerator(int id) {
		super(id, Material.iron);
		setUnlocalizedName(Names.generatorUnlocal);
		setCreativeTab(TestLab.tabTestLab);
		setHardness(5F);
		setResistance(10F);
		}
}
