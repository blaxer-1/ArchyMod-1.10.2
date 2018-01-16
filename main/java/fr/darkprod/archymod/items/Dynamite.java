package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.entity.EntityDynamite;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dynamite extends Item{
  public Dynamite(String name)
  {
    setRegistryName(name);
    setUnlocalizedName(name);
    setCreativeTab(ArchyMod.ArchyMod);
  }
  
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
  {
	worldIn.playSound(playerIn, playerIn.getPosition(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 10F, 10F);
    ItemStack itemStack = new ItemStack(itemStackIn.getItem(), itemStackIn.stackSize - 1);
    if (EnumHand.MAIN_HAND.equals(hand)) {
      playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, itemStack);
    } else if (EnumHand.OFF_HAND.equals(hand)) {
      playerIn.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, itemStack);
    }
    worldIn.spawnEntityInWorld(new EntityDynamite(worldIn, playerIn, playerIn.getHeldItem(hand)));
    
    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
  }
}
