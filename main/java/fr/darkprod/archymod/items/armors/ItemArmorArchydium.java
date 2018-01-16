package fr.darkprod.archymod.items.armors;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemArmorArchydium extends ItemArmor
{
	public ItemArmorArchydium(String name, int renderIndexIn,ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlotIn)
	{
		super(materialIn, renderIndexIn, equipmentSlotIn);

		this.setRegistryName(name);
		this.setUnlocalizedName(name);

		this.setCreativeTab(ArchyMod.ArchyMod);
		this.setMaxStackSize(1);
	}
	public void onArmorTick(World w, EntityPlayer p, ItemStack i) {
		if (this == ModItems.ArchydiumHelmet) {
			p.addPotionEffect(new PotionEffect(Potion.getPotionById(16),660 , 1));
		}
		if (this == ModItems.ArchydiumChestplate) {
			p.addPotionEffect(new PotionEffect(Potion.getPotionById(1),660 , 1));
		}
		if (this == ModItems.ArchydiumLeggings) {
			p.addPotionEffect(new PotionEffect(Potion.getPotionById(11),660 , 1));
		}
		if (this == ModItems.ArchydiumBoots) {
			p.addPotionEffect(new PotionEffect(Potion.getPotionById(5),660 , 1));
		}
	}

	public void onArmorTick(Entity e,EntityPlayer p,ItemStack i) {
		if(p.attackEntityAsMob(e)) {
			i.damageItem(1, p);
		}
	}
}
