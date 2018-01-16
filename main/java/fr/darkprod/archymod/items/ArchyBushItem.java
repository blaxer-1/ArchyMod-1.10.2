package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ArchyBushItem extends Item
{
  public ArchyBushItem(String name)
  {
	  setRegistryName(name);
	  setUnlocalizedName(name);
	  setCreativeTab(ArchyMod.ArchyMod);
	  setMaxStackSize(64);
  }
  @Override
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
  {
    ItemStack itemStack = new ItemStack(itemStackIn.getItem(), itemStackIn.stackSize - 1);
    if (EnumHand.MAIN_HAND.equals(hand)) {
      playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, itemStack);
    } else if (EnumHand.OFF_HAND.equals(hand)) {
      playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, itemStack);
    }
    playerIn.addExperience(2);
    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
  }
}