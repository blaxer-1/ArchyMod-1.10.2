package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.item.Item;

public class Arzot extends Item {

	public Arzot(String name) {
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(ArchyMod.ArchyMod);
		setMaxStackSize(64);
	}
	
}
