package fr.darkprod.archymod.gui;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.container.ContainerBackPack;
import fr.darkprod.archymod.container.ContainerInventoryFurnace;
import fr.darkprod.archymod.inventory.InventoryBackPack;
import fr.darkprod.archymod.tiles.TileInventoryFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler
  implements IGuiHandler
{
  public GuiHandler() {}
  
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch (ID)
    {
    case ArchyMod.GUI_BACKPACK:
    	return new ContainerBackPack(player.inventory, new InventoryBackPack(player.inventory.getCurrentItem()) , player);
    
    case ArchyMod.GUI_FURNACE:
	BlockPos xyz = new BlockPos(x, y, z);
	TileEntity tileEntity = world.getTileEntity(xyz);
	if (tileEntity instanceof TileInventoryFurnace) {
		TileInventoryFurnace tileInventoryFurnace = (TileInventoryFurnace) tileEntity;
		return new ContainerInventoryFurnace(player.inventory, tileInventoryFurnace);
	}
    }
    return null;
  }
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    switch (ID)
    {
    case ArchyMod.GUI_BACKPACK:
    	return new GuiBackPack(new InventoryBackPack(player.inventory.getCurrentItem()));
    case ArchyMod.GUI_FURNACE:
    BlockPos xyz = new BlockPos(x, y, z);
	TileEntity tileEntity = world.getTileEntity(xyz);
	if (tileEntity instanceof TileInventoryFurnace) {
		TileInventoryFurnace tileInventoryFurnace = (TileInventoryFurnace) tileEntity;
		return new GuiInventoryFurnace(player.inventory, tileInventoryFurnace);
	}
    }
    return null;
  }
}
