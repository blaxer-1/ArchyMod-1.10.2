package fr.darkprod.archymod.items;

import org.apache.commons.lang3.RandomUtils;

import fr.darkprod.archymod.ArchyMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.server.SPacketBlockChange;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class ItemHammer
  extends ItemPickaxe
{
  public long time = System.currentTimeMillis();
  protected static final Material[] MATERIALS = { Material.ROCK, Material.IRON, Material.ICE, Material.GLASS, Material.PISTON, Material.ANVIL, Material.SNOW, Material.CRAFTED_SNOW, Material.CLAY};
  
  public ItemHammer(String name,Item.ToolMaterial material)
  {
    super(material);
    setCreativeTab(ArchyMod.ArchyMod);
    setRegistryName(name);
    setUnlocalizedName(name);
  }
  
  
  public boolean isBookEnchantable(ItemStack stack, ItemStack book)
  {
    return true;
  }
  
  public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
  {
    return false;
  }
  public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, EntityPlayer player)
  {
    World world = player.worldObj;
    IBlockState state = world.getBlockState(pos);
    Block block = state.getBlock();
    RayTraceResult object = raytraceFromEntity(world, player, false, 4.5D);
    if (object == null) {
      return super.onBlockDestroyed(stack, world, state, pos, player);
    }
    EnumFacing side = object.sideHit;
    int xmove = 0;
    int ymove = 0;
    int zmove = 0;
    if ((side == EnumFacing.UP) || (side == EnumFacing.DOWN))
    {
      xmove = 1;
      zmove = 1;
    }
    else
    {
      ymove = 1;
      if ((side == EnumFacing.WEST) || (side == EnumFacing.EAST)) {
        zmove = 1;
      } else {
        xmove = 1;
      }
    }
    float strength = ForgeHooks.blockStrength(state, player, world, pos);
    
    int x = pos.getX();
    int y = pos.getY();
    int z = pos.getZ();
    for (int i = -xmove; i <= xmove; i++) {
      for (int j = -ymove; j <= ymove; j++) {
        for (int k = -zmove; k <= zmove; k++) {
          if ((x + i != x) || (y + j != y) || (z + k != z)) {
            checkBlockBreak(world, player, new BlockPos(x + i, y + j, z + k), stack, strength, block, side);
          }
        }
      }
    }
    return false;
  }
  public void checkBlockBreak(World world, EntityPlayer player, BlockPos pos, ItemStack stack, float strength, Block originalBlock, EnumFacing side)
  {
    IBlockState state = world.getBlockState(pos);
    Block breakBlock = state.getBlock();
    Material material = originalBlock.getMaterial(state);
    if ((breakBlock.getMaterial(state) == material) && (ForgeHooks.canHarvestBlock(breakBlock, player, world, pos)) && 
      (stack.canHarvestBlock(state)))
    {
      float newStrength = ForgeHooks.blockStrength(state, player, world, pos);
      if ((newStrength > 0.0F) && (strength / newStrength <= 10.0F)) {
        if (breakBlock.getBlockHardness(state, world, pos) != 0.0D)
        {
          if (handleDamage(false, state, stack, player)) {
            tryHarvestBlock(world, state, pos, side, player);
          }
        }
        else {
          tryHarvestBlock(world, state, pos, side, player);
        }
      }
    }
  }
  public static boolean handleDamage(boolean force, IBlockState state, ItemStack stack, EntityLivingBase entityLiving)
  {
    return requestDamage(force, state, stack, entityLiving, 1);
  }
  public static boolean requestDamage(boolean force, IBlockState state, ItemStack stack, EntityLivingBase entityLiving, int damage)
  {
    if ((entityLiving instanceof EntityPlayer))
    {
      EntityPlayer player = (EntityPlayer)entityLiving;
      if (player.capabilities.isCreativeMode) {
        return true;
      }
      
      
    }
    return handleRegularDamage(force, stack, damage, entityLiving);
  }
  private static boolean handleRegularDamage(boolean force, ItemStack stack, int damage, EntityLivingBase entityLiving)
  {
    if ((!force) && (stack.getMaxDamage() - stack.getItemDamage() < damage)) {
      return false;
    }
    stack.damageItem(damage, entityLiving);
    
    return true;
  }
    public static boolean tryHarvestBlock(World world, IBlockState state, BlockPos pos, EnumFacing side, EntityPlayer player)
    {
      Block block = state.getBlock();
      if (block.isAir(state, world, pos)) {
        return false;
      }
      EntityPlayerMP playerMP = null;
      if ((player instanceof EntityPlayerMP)) {
        playerMP = (EntityPlayerMP)player;
      }
      ItemStack item = player.getHeldItemMainhand();
      if ((item == null) || (item.getItem() == null)) {
        return false;
      }
      if ((!item.getItem().getToolClasses(item).contains(block.getHarvestTool(state))) && (item.getItem().getStrVsBlock(item, state) <= 1.0F)) {
        return false;
      }
      if (!ForgeHooks.canHarvestBlock(block, player, world, pos)) {
        return false;
      }
      int event = 0;
      if (playerMP != null)
      {
        event = ForgeHooks.onBlockBreakEvent(world, world.getWorldInfo().getGameType(), playerMP, pos);
        if (event == -1) {
          return false;
        }
      }
      world.playEvent(playerMP, 2001, pos, Block.getStateId(state));
      if (player.capabilities.isCreativeMode)
      {
        if (!world.isRemote) {
          block.onBlockHarvested(world, pos, state, player);
        }
        if (block.removedByPlayer(state, world, pos, player, false)) {
          block.onBlockDestroyedByPlayer(world, pos, state);
        }
        if (!world.isRemote) {
          playerMP.connection.sendPacket(new SPacketBlockChange(world, pos));
        } else {
          Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, side));
        }
        return true;
      }
      if (!world.isRemote)
      {
        block.onBlockHarvested(world, pos, state, player);
        if (block.removedByPlayer(state, world, pos, player, true))
        {
          block.onBlockDestroyedByPlayer(world, pos, state);
          block.harvestBlock(world, player, pos, state, null, item);
          block.dropXpOnBlockBreak(world, pos, event);
        }
        playerMP.connection.sendPacket(new SPacketBlockChange(world, pos));
      }
      else
      {
        if (block.removedByPlayer(state, world, pos, player, true)) {
          block.onBlockDestroyedByPlayer(world, pos, state);
        }
        Minecraft.getMinecraft().getConnection().sendPacket(new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, pos, side));
      }
      return true;
    }
    public static RayTraceResult raytraceFromEntity(World world, Entity player, boolean par3, double range)
    {
      float f = 1.0F;
      float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
      float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
      double d0 = player.prevPosX + (player.posX - player.prevPosX) * f;
      double d1 = player.prevPosY + (player.posY - player.prevPosY) * f;
      if ((!world.isRemote) && ((player instanceof EntityPlayer))) {
        d1 += 1.62D;
      }
      double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * f;
      
      Vec3d vec3 = new Vec3d(d0, d1, d2);
      
      float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.cos(-f1 * 0.017453292F);
      float f6 = MathHelper.sin(-f1 * 0.017453292F);
      float f7 = f4 * f5;
      float f8 = f3 * f5;
      double d3 = range;
      if ((player instanceof EntityPlayerMP)) {
        d3 = ((EntityPlayerMP)player).interactionManager.getBlockReachDistance();
      }
      Vec3d vec31 = vec3.addVector(f7 * d3, f6 * d3, f8 * d3);
      
      return world.rayTraceBlocks(vec3, vec31, par3, !par3, par3);
    }
}

