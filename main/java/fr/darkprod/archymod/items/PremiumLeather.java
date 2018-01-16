package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.item.Item;

public class PremiumLeather extends Item {
	public PremiumLeather(String name) {
		
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
	}
}
