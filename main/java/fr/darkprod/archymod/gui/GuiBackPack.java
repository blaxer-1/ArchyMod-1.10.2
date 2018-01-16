package fr.darkprod.archymod.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;

public class GuiBackPack extends GuiChest {

	public GuiBackPack(IInventory lowerInv) {
		super(Minecraft.getMinecraft().thePlayer.inventory, lowerInv);

	}

}
