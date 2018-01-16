package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.item.ItemSword;

public class ItemArchydiumSword extends ItemSword {

	public ItemArchydiumSword(String name,ToolMaterial material,float speed , float damage) {
		super(material);

		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
		
	}

}
