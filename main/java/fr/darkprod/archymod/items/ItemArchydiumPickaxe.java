package fr.darkprod.archymod.items;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.item.ItemPickaxe;

public class ItemArchydiumPickaxe extends ItemPickaxe {

	public ItemArchydiumPickaxe(String name,ToolMaterial material, float speed, float damage) {
		super(material);
		
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ArchyMod.ArchyMod);
		
	}

}
