package fr.darkprod.archymod.items;

import java.util.ArrayList;
import java.util.List;

import fr.darkprod.archymod.ArchyMod;
import fr.darkprod.archymod.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ArchyHang
  extends Item
{
  public static List<EntityPlayer> usingHangGliderClient = new ArrayList();
  public static List<EntityPlayer> usingHangGliderServer = new ArrayList();
  
  public ArchyHang(String name)
  {
    setMaxStackSize(1);
    setUnlocalizedName(name);
    setCreativeTab(ArchyMod.ArchyMod);
    setRegistryName(name);
  }
  
  public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
  {
    if (worldIn.isRemote)
    {
      if (!usingHangGliderClient.contains(playerIn))
      {
        usingHangGliderClient.add(playerIn);
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
      }
      usingHangGliderClient.remove(playerIn);
    }
    if (!worldIn.isRemote)
    {
      if (!usingHangGliderServer.contains(playerIn))
      {
        usingHangGliderServer.add(playerIn);
        return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
      }
      usingHangGliderServer.remove(playerIn);
    }
    return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
  }
  
  public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
  {
    if (!(entityIn instanceof EntityPlayer)) {
      return;
    }
    EntityPlayer player = (EntityPlayer)entityIn;
    if (worldIn.isRemote)
    {
      if (player.isCollidedVertically) {
        usingHangGliderClient.remove(player);
      }
      if ((usingHangGliderClient.contains(player)) && 
        (player.motionY < 0.0D))
      {
        double verticalSpeed;
        double horizontalSpeed;
        
        
        if (player.isSneaking())
        {
          horizontalSpeed = 0.1D;
          verticalSpeed = 0.9D;
        }
        else
        {
          horizontalSpeed = 0.03D;
          verticalSpeed = 0.7D;
        }
        player.motionY *= verticalSpeed;
        double x = Math.cos(Math.toRadians(player.rotationYawHead + 90.0F)) * horizontalSpeed;
        double z = Math.sin(Math.toRadians(player.rotationYawHead + 90.0F)) * horizontalSpeed;
        player.motionX += x;
        player.motionZ += z;
        player.fallDistance = 0.0F;
      }
    }
    if (!worldIn.isRemote)
    {
      if (player.isCollidedVertically) {
        usingHangGliderServer.remove(player);
      }
      if ((usingHangGliderServer.contains(player)) && 
        (player.motionY < 0.0D))
      {
        double verticalSpeed;
        double horizontalSpeed;
        if (player.isSneaking())
        {
        	horizontalSpeed = 0.1D;
        	verticalSpeed = 0.7D;
        }
        else
        {
          horizontalSpeed = 0.03D;
          verticalSpeed = 0.4D;
        }
        player.motionY *= verticalSpeed;
        double x = Math.cos(Math.toRadians(player.rotationYawHead + 90.0F)) * horizontalSpeed;
        double z = Math.sin(Math.toRadians(player.rotationYawHead + 90.0F)) * horizontalSpeed;
        player.motionX += x;
        player.motionZ += z;
        player.fallDistance = 0.0F;
      }
    }
  }
}