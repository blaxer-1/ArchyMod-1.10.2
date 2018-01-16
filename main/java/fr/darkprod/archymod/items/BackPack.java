package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.inventory.InventoryBackPack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class BackPack extends Item {
	public BackPack(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(ArchyMod.ArchyMod);
		setMaxStackSize(1);
	}
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
		super.onCreated(stack, worldIn, playerIn);
		new InventoryBackPack(stack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,EnumHand hand) {
		if (!worldIn.isRemote) {
			playerIn.openGui(ArchyMod.instance, ArchyMod.GUI_BACKPACK, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
		}
		return new ActionResult(EnumActionResult.PASS, itemStackIn);
		
	}
}
