package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.init.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ArchydiumAxe extends ItemAxe {

	public ArchydiumAxe(String name,ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
	}
	private int breakBlock = 0;
	  
	  public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
	  {	
		  if ((!worldIn.isRemote) && (
	      (worldIn.getBlockState(pos).getBlock().equals(Blocks.LOG)) || (worldIn.getBlockState(pos).getBlock().equals(Blocks.LOG2)))) {
	      if ((entityLiving.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) != null) && (entityLiving.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND).getItem().equals(this)))
	      {
	        ItemStack itemStack = stack;
	        itemStack.damageItem(50, entityLiving);
	        entityLiving.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, itemStack);
	        worldIn.destroyBlock(pos, true);
	        DestroyBlock(pos, worldIn);
	        this.breakBlock = 0;
	      }
	      else if ((entityLiving.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND) != null) && (entityLiving.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND).getItem().equals(this)))
	      {
	        ItemStack itemStack = stack;
	        itemStack.damageItem(50, entityLiving);
	        entityLiving.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, itemStack);
	        worldIn.destroyBlock(pos, true);
	        DestroyBlock(pos, worldIn);
	        this.breakBlock = 0;
	      }
	    }
	    return true;
	  }
	  
	  public void DestroyBlock(BlockPos blockPos, World world)
	  {
	    int blockMax = 100;
	    BlockPos blockPosX1 = new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ());
	    BlockPos blockPosX0 = new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ());
	    BlockPos blockPosY1 = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
	    BlockPos blockPosY0 = new BlockPos(blockPos.getX(), blockPos.getY() - 1, blockPos.getZ());
	    BlockPos blockPosZ1 = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1);
	    BlockPos blockPosZ0 = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1);
	    if (((world.getBlockState(blockPosX1).getBlock().equals(Blocks.LOG)) || (world.getBlockState(blockPosX1).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosX1, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosX1, world);
	    }
	    if (((world.getBlockState(blockPosX0).getBlock().equals(Blocks.LEAVES)) || (world.getBlockState(blockPosX0).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosX0, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosX0, world);
	    }
	    if (((world.getBlockState(blockPosY1).getBlock().equals(Blocks.LOG)) || (world.getBlockState(blockPosY1).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosY1, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosY1, world);
	    }
	    if (((world.getBlockState(blockPosY0).getBlock().equals(Blocks.LOG)) || (world.getBlockState(blockPosY0).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosY0, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosY0, world);
	    }
	    if (((world.getBlockState(blockPosZ1).getBlock().equals(Blocks.LOG)) || (world.getBlockState(blockPosZ1).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosZ1, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosZ1, world);
	    }
	    if (((world.getBlockState(blockPosZ0).getBlock().equals(Blocks.LOG)) || (world.getBlockState(blockPosZ0).getBlock().equals(Blocks.LOG2))) && 
	      (100 >= this.breakBlock))
	    {
	      world.destroyBlock(blockPosZ0, true);
	      this.breakBlock += 1;
	      DestroyBlock(blockPosZ0, world);
	    }
	  }

}
