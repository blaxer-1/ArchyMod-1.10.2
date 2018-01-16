package fr.darkprod.archymod.init;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.blocks.BlockArchyBush;
import fr.darkprod.archymod.blocks.BlockInventoryFurnace;
import fr.darkprod.archymod.blocks.BlockMod;
import fr.darkprod.archymod.blocks.BlockOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;

public class ModBlocks
{
  public static Block oreArchydium;
  public static Block ArchydiumBlock;
  public static Block ArpazOre;
  public static Block ArpazBlock;
  public static Block ArchydiumFurnace;
  public static Block ArchyBush;
  public static Block ArzotOre;
  public static Block commandBlock;
  public static Block invisibleBlock;
 
  public static void init()
  {
	invisibleBlock = Blocks.BARRIER.setCreativeTab(ArchyMod.ArchyMod);
	commandBlock = Blocks.COMMAND_BLOCK.setCreativeTab(ArchyMod.ArchyMod);
    oreArchydium = new BlockOre("oreArchydium");
    ArpazOre = new BlockOre("ArpazOre");
    ArchydiumBlock = new BlockMod(Material.ROCK, "ArchydiumBlock");
    ArpazBlock = new BlockMod(Material.ROCK, "ArpazBlock");
    ArzotOre = new BlockOre("ArzotOre");
    ArchydiumFurnace = new BlockInventoryFurnace();
    ArchyBush = new BlockArchyBush();
  }
}
