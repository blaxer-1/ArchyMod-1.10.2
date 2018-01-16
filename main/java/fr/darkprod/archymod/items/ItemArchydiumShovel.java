package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.item.ItemSpade;

public class ItemArchydiumShovel extends ItemSpade {

	public ItemArchydiumShovel(String name,ToolMaterial material,float speed , float damage) {
		super(material);
		setRegistryName(name);
		setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
	}

	

}
