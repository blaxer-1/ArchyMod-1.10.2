package fr.darkprod.archymod.blocks;

import fr.darkprod.archymod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class BlockRenderRegister
{
  public static void init()
  {
    register(ModBlocks.oreArchydium);
    register(ModBlocks.ArchyBush);
    register(ModBlocks.ArchydiumBlock);
    register(ModBlocks.ArchydiumFurnace);
    register(ModBlocks.ArpazBlock);
    register(ModBlocks.ArzotOre);
    register(ModBlocks.ArpazOre);
  }
  
  public static void register(Block block)
  {
    Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation("archydium:" + block
      .getUnlocalizedName().substring(5), "inventory"));
  }
}
