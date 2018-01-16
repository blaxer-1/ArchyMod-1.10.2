package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemArchydiumSteak extends ItemFood {

	public ItemArchydiumSteak(String name, int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		setCreativeTab(ArchyMod.ArchyMod);
		setRegistryName(name);
		setUnlocalizedName(name);
	}
	protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player)
	  {
	    super.onFoodEaten(stack, world, player);
	    if (!world.isRemote)
	    {
	      player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 2));
	      player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 500, 1));
	      //player.addChatMessage(new TextComponentString(TextFormatting.GREEN+"Fuyez !!!!"));
	    }
	  }
	 public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	  {
	    playerIn.setActiveHand(hand);
	    return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
	  }

}
