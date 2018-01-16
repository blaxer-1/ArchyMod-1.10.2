package fr.darkprod.archymod;

import fr.darkprod.archymod.fuel.IFuelHandlerArchydium;
import fr.darkprod.archymod.gen.GenOres;
import fr.darkprod.archymod.gui.GuiHandler;
import fr.darkprod.archymod.init.ModBlocks;
import fr.darkprod.archymod.init.ModItems;
import fr.darkprod.archymod.init.ModRecipies;
import fr.darkprod.archymod.proxy.CommonProxy;
import fr.darkprod.archymod.tiles.TileInventoryFurnace;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = References.MOD_ID, name = References.MOD_NAME, version = References.VERSION, acceptedMinecraftVersions = "[1.10, 1.10.2]")

public class ArchyMod {
	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY)
	public static CommonProxy proxy;

	@Mod.Instance(References.MOD_ID)
	public static ArchyMod instance;
	
	public static final int GUI_BACKPACK = 0;
	public static final int GUI_FURNACE = 1;
	

	public static CreativeTabs ArchyMod = new CreativeTabs("ArchyMod") {

		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return ModItems.ArchydiumIngot;
		}
		
		
	};

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		proxy.preInit();
		ModItems.init();
		ModItems.register();
		ModBlocks.init();
		GameRegistry.registerFuelHandler(new IFuelHandlerArchydium());
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent e)
	{
		proxy.init();
		GameRegistry.registerTileEntity(TileInventoryFurnace.class, "TileArchydiumFurnace");
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		GameRegistry.registerWorldGenerator(new GenOres(), 0);
		OreDictionary.registerOre("oreArchydium", ModBlocks.oreArchydium);
		OreDictionary.registerOre("oreArpaz", ModBlocks.ArpazOre);
		OreDictionary.registerOre("oreArzot", ModBlocks.ArzotOre);
		ModRecipies.init();
		
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e)
	{
		proxy.postInit();
	}
}