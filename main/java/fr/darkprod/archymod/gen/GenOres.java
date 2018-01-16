package fr.darkprod.archymod.gen;

import java.util.Random;

import fr.darkprod.archymod.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class GenOres
  implements IWorldGenerator
{
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
  {
    if ((world.provider.getDimensionType() == DimensionType.OVERWORLD)) {
      genOres(world, chunkX, chunkZ, random);
    }
  }
  
  private void genOres(World world, int chunkX, int chunkZ, Random random)
  {
    genOre(world, ModBlocks.oreArchydium.getDefaultState(), chunkX, chunkZ, 4, 3, 5, 0, 15, Blocks.STONE
      .getDefaultState(), random);
    genOre(world, ModBlocks.ArpazOre.getDefaultState(), chunkX, chunkZ, 4, 3, 5, 0, 15, Blocks.STONE
      .getDefaultState(), random);
    genOre(world, ModBlocks.ArzotOre.getDefaultState(), chunkX, chunkZ, 3, 5, 7, 0, 25, Blocks.STONE
      .getDefaultState(), random);
    genOre(world, ModBlocks.ArchyBush.getDefaultState(), chunkX, chunkZ, 5, 2, 5, 0, 15, Blocks.STONE
      .getDefaultState(), random);
  }
  
  private void genOre(World world, IBlockState block, int chunkX, int chunkZ, int chance, int veineMinSize, int veineMaxSize, int heightMin, int heightMax, IBlockState generateIn, Random random)
  {
    int veineRange = veineMaxSize - veineMinSize;
    int veineSize = random.nextInt(veineRange + 1) + veineMinSize;
    
    int heightRange = heightMax - heightMin;
    
    WorldGenMinable gen = new WorldGenMinable(block, veineSize);
    for (int i = 0; i < chance; i++)
    {
      int xRand = chunkX * 16 + random.nextInt(16);
      int yRand = random.nextInt(heightRange) + heightMin;
      int zRand = chunkZ * 16 + random.nextInt(16);
      gen.generate(world, random, new BlockPos(xRand, yRand, zRand));
    }
  }
}
