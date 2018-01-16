package fr.darkprod.archymod.blocks;

import javax.annotation.Nullable;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.tiles.TileInventoryFurnace;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityEnchantmentTableRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

	public class BlockInventoryFurnace
	  extends BlockHorizontal
	{
	  public BlockInventoryFurnace()
	  {
	    super(Material.ROCK);
	    
	    setCreativeTab(ArchyMod.ArchyMod);
	    setUnlocalizedName("ArchydiumFurnace");
	    setResistance(4.0F);
	    setHardness(4.0F);
	    setHarvestLevel("pickaxe", 2);
	    setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	    setRegistryName(new ResourceLocation("archydium", "archydiumFurnace"));
	    GameRegistry.register(this);
	    GameRegistry.register(new ItemBlock(this), getRegistryName());
	  }
	  
	  public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	  {
	    IBlockState state = super.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer);
	    return state.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	  }
	  
	  public int getMetaFromState(IBlockState state)
	  {
	    return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
	  }
	  
	  public IBlockState getStateFromMeta(int meta)
	  {
	    return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	  }
	  
	  protected BlockStateContainer createBlockState()
	  {
	    return new BlockStateContainer(this, new IProperty[] { FACING });
	  }
	  
	  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	  {
	    if (worldIn.isRemote) {
	      return true;
	    }
	    playerIn.openGui(ArchyMod.instance, ArchyMod.GUI_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
	    return true;
	  }
	  
	  public TileEntity createTileEntity(World world, IBlockState state)
	  {
	    return new TileInventoryFurnace();
	  }
	  
	  public boolean hasTileEntity(IBlockState state)
	  {
	    return true;
	  }
	  
	  public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	  {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	    if ((tileentity instanceof IInventory))
	    {
	      InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
	      worldIn.updateComparatorOutputLevel(pos, this);
	    }
	    worldIn.setTileEntity(pos, null);
	    super.breakBlock(worldIn, pos, state);
	  }
}
