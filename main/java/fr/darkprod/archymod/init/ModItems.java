package fr.darkprod.archymod.init;

import fr.darkprod.archymod.References;
import fr.darkprod.archymod.items.*;
import fr.darkprod.archymod.items.armors.ItemArmorArchydium;
import io.netty.handler.codec.compression.CompressionException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ModItems
{
	public static final ArmorMaterial ArchydiumMat = EnumHelper.addArmorMaterial("Archydium", References.MOD_ID + ":archydium", 53, new int[]{2, 3, 4, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);
	public static final ToolMaterial ARCHYDIUM = EnumHelper.addToolMaterial("ARCHYDIUM",2, 3000, 10, 4, 25);
	
	public static Item archydiumSteak;
	public static Item ArchyBushItem ;
	public static Item Dynamite;
	public static Item ArchyHammer;
	public static Item BackPack; 
	public static Item ArchyHang;
	public static Item ArchydiumIngot; 
	public static Item PremiumLeather;
	public static Item ArpazIngot;
	public static Item ArchyPower;
	public static Item ArchydiumStick ;
	public static Item CompressedArchydium;
	public static Item Arzot ;
	public static Item ArchydiumAxe;
	public static Item ArchydiumPickaxe;
	public static Item ArchydiumSword;
	public static Item ArchydiumShovel;


	public static Item ArchydiumHelmet;
	public static Item ArchydiumChestplate;
	public static Item ArchydiumLeggings; 
	public static Item ArchydiumBoots;


	
	public static void init()
	{
		archydiumSteak = new ItemArchydiumSteak("archydiumSteak", 5, 100.0F, false);
		ArchyBushItem = new ArchyBushItem("ArchyBushItem");
		Dynamite = new Dynamite("Dynamite");
		ArchyHammer = new ItemHammer("ArchyHammer",ARCHYDIUM);
		BackPack = new BackPack("BackPack");
		ArchyHang  = new ArchyHang("ArchyHang");
		ArchydiumIngot = new ArchydiumIngot("ArchydiumIngot");
		PremiumLeather = new PremiumLeather("PremiumLeather");
		ArpazIngot = new ArpazIngot("ArpazIngot");
		ArchyPower  = new ArchyPower("ArchyPower");
		ArchydiumStick = new ArchydiumStick("ArchydiumStick");
		Arzot  = new Arzot("Arzot");
		CompressedArchydium = new CompressedArchydium("CompressedArchydium");
		ArchydiumAxe = new ArchydiumAxe("ArchydiumAxe", ARCHYDIUM,6.0F, 10.0F);
		ArchydiumPickaxe  = new ItemArchydiumPickaxe("ArchydiumPickaxe", ARCHYDIUM,6.0F,2.0F);
		ArchydiumSword  = new ItemArchydiumSword("ArchydiumSword", ARCHYDIUM,10.0F, 3.0F);	
		ArchydiumShovel = new ItemArchydiumShovel("ArchydiumShovel", ARCHYDIUM,6.0F,2.0F);
		
		ArchydiumHelmet = new ItemArmorArchydium("ArchydiumHelmet",1, ArchydiumMat, EntityEquipmentSlot.HEAD);
		ArchydiumChestplate = new ItemArmorArchydium("ArchydiumChestplate",1, ArchydiumMat, EntityEquipmentSlot.CHEST);
		ArchydiumLeggings = new ItemArmorArchydium("ArchydiumLeggings",2, ArchydiumMat, EntityEquipmentSlot.LEGS);
		ArchydiumBoots  = new ItemArmorArchydium("ArchydiumBoots",1, ArchydiumMat, EntityEquipmentSlot.FEET);
	
		BackPack = new BackPack("ArchyPack");
	}

	public static void register()
	{

		GameRegistry.register(archydiumSteak);
		GameRegistry.register(ArchyHammer);
		GameRegistry.register(ArchyBushItem);
		GameRegistry.register(Dynamite);
		GameRegistry.register(Arzot);
		GameRegistry.register(BackPack);
		GameRegistry.register(ArchyHang);
		
		
		GameRegistry.register(ArchydiumAxe);
		GameRegistry.register(ArchydiumPickaxe);
		GameRegistry.register(ArchydiumShovel);
		GameRegistry.register(ArchydiumSword);

		GameRegistry.register(ArchydiumHelmet);
		GameRegistry.register(ArchydiumChestplate);
		GameRegistry.register(ArchydiumLeggings);
		GameRegistry.register(ArchydiumBoots);
		

		GameRegistry.register(ArchydiumIngot);
		GameRegistry.register(PremiumLeather);
		GameRegistry.register(ArpazIngot);
		GameRegistry.register(ArchyPower);
		GameRegistry.register(ArchydiumStick);
		GameRegistry.register(CompressedArchydium);
		
	

	}

	public static void registerRenders()
	{
		registerRender(archydiumSteak);
		registerRender(ArchydiumHelmet);
		registerRender(ArchyHammer);
		registerRender(ArchyBushItem);
		registerRender(ArchydiumChestplate);
		registerRender(ArchydiumLeggings);
		registerRender(ArchydiumBoots);
		registerRender(ArchydiumIngot);
		registerRender(PremiumLeather);
		registerRender(ArpazIngot);
		registerRender(ArchyPower);
		registerRender(ArchydiumStick);
		registerRender(CompressedArchydium);
		registerRender(ArchydiumAxe);
		registerRender(ArchyHang);
		registerRender(ArchydiumPickaxe);
		registerRender(ArchydiumShovel);
		registerRender(ArchydiumSword);
		registerRender(Arzot);
		registerRender(Dynamite);
		registerRender(BackPack);
		registerRender(BackPack);

	}

	private static void registerRender(Item item)
	{
		//System.out.println("Item(s) enregistré(s) : "+item.getRegistryName());
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}


}
