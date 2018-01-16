package fr.darkprod.archymod.blocks;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockMod
  extends Block
{
  public BlockMod(Material material, String name)
  {
    super(material);
    setUnlocalizedName(name);
    setRegistryName(new ResourceLocation("archydium", name));
    GameRegistry.register(this);
    registerItemForm();
    setCreativeTab(ArchyMod.ArchyMod);
    setResistance(10.0F);
    setHardness(10.0F);
  }
  
  public void registerItemForm()
  {
    GameRegistry.register(new ItemBlock(this), getRegistryName());
  }
}
