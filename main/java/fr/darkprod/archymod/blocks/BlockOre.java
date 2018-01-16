package fr.darkprod.archymod.blocks;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockOre
  extends BlockMod
{
  
  public BlockOre(String name)
  {
    super(Material.ROCK, name);
    setResistance(4.0F);
    setHardness(4.0F);
  }
  
  
}
