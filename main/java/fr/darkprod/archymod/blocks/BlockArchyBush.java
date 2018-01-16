package fr.darkprod.archymod.blocks;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.init.ModItems;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockArchyBush
  extends BlockContainer
{
  public static final PropertyInteger LVL = PropertyInteger.create("lvl", 0, 15);
  
  public BlockArchyBush()
  {
    super(Material.GLASS);
    setUnlocalizedName("ArchyBush");
    setResistance(1.0F);
    setHardness(1.0F);
    setCreativeTab(ArchyMod.ArchyMod);
    setDefaultState(this.blockState.getBaseState().withProperty(LVL, Integer.valueOf(0)));
    setTickRandomly(true);
    
    setRegistryName(new ResourceLocation("archydium", "ArchyBush"));
    GameRegistry.register(this);
    GameRegistry.register(new ItemBlock(this), getRegistryName());
  }
  
  public IBlockState getStateFromMeta(int meta)
  {
    return getDefaultState().withProperty(LVL, Integer.valueOf(meta));
  }
  
  public EnumBlockRenderType getRenderType(IBlockState state)
  {
    return EnumBlockRenderType.MODEL;
  }
  
  protected BlockStateContainer createBlockState()
  {
    return new BlockStateContainer(this, new IProperty[] { LVL });
  }
  
  public int getMetaFromState(IBlockState state)
  {
    return ((Integer)state.getValue(LVL)).intValue();
  }
  
  public boolean isOpaqueCube(IBlockState state)
  {
    return false;
  }
  
  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
  {
    float minZ;
    float minX = minZ = 0.125F;
    float maxZ;
    float maxX = maxZ = 0.875F;
    float maxY = 1.0F;
    
    return new AxisAlignedBB(minX, 0.0D, minZ, maxX, maxY, maxZ);
  }
  
  public TileEntity createNewTileEntity(World worldIn, int meta)
  {
    return null;
  }
  
  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
  {
    if ((!worldIn.isRemote) && 
      (((Integer)state.getValue(LVL)).intValue() == 15))
    {
      worldIn.spawnEntityInWorld(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.ArchyBushItem, 1)));
      worldIn.setBlockState(pos, state.withProperty(LVL, Integer.valueOf(0)));
    }
    return true;
  }
  
  public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
  {
    if ((!worldIn.isRemote) && 
      (((Integer)state.getValue(LVL)).intValue() != 15)) {
      worldIn.setBlockState(pos, state.withProperty(LVL, Integer.valueOf(((Integer)state.getValue(LVL)).intValue() + 1)));
    }
  }
  
  public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
  {
    if ((!worldIn.isRemote) && 
      ((entityIn instanceof EntityPlayer)))
    {
      EntityPlayer entityPlayer = (EntityPlayer)entityIn;
      entityPlayer.attackEntityFrom(DamageSource.cactus, 2.0F);
    }
  }
}

