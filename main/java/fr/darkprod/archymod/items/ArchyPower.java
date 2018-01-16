package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ArchyPower extends Item {
	
	public ArchyPower(String name) {
		
		
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
		this.maxStackSize = 1;
		this.setMaxDamage(5);
		
		}
		
		public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer player, EnumHand hand)
	    {
	        player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 100, 3));
	        player.addPotionEffect(new PotionEffect(Potion.getPotionById(12), 200, 5));
	        player.addPotionEffect(new PotionEffect(Potion.getPotionById(5), 40, 3));
	        player.addPotionEffect(new PotionEffect(Potion.getPotionById(10), 20, 5));
	        itemStack.damageItem(1, player);
	        return new ActionResult(EnumActionResult.PASS, itemStack);
	    }
	    
		  
		  public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
		  {
		      if (stack.getItemDamage() >= 	5) {
		        entityIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
		      }
		    }
}

