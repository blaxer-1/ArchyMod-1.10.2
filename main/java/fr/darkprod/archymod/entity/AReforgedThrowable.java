package fr.darkprod.archymod.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class AReforgedThrowable
  extends EntityThrowable
{
  public static final DataParameter<Boolean> INITIATED = EntityDataManager.createKey(AReforgedThrowable.class, DataSerializers.BOOLEAN);
  private final String damageName;
  
  public AReforgedThrowable(World worldIn, EntityLivingBase thrower, ItemStack stack, String damageName)
  {
    super(worldIn, thrower);
    this.damageName = damageName;
    setLocationAndAngles(thrower.posX, thrower.posY + thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
    
    this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.25D;
    this.posY -= 0.10000000149011612D;
    this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.25D;
    setPosition(this.posX, this.posY, this.posZ);
    
    this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
    
    this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F));
    this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F));
    setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
  }
  
  public AReforgedThrowable(World worldIn, String damageName)
  {
    super(worldIn);
    this.damageName = damageName;
  }
  
  protected DamageSource causeImpactDamage(Entity target, EntityLivingBase shooter)
  {
    return new EntityDamageSourceIndirect(this.damageName, target, shooter).setProjectile();
  }
  
  public boolean creativeUse()
  {
    return creativeUse(getThrower());
  }
  
  public boolean creativeUse(Entity e)
  {
    return (((e instanceof EntityPlayer)) && (((EntityPlayer)e).capabilities.isCreativeMode)) || (!(e instanceof EntityPlayer));
  }
  
  protected void entityInit()
  {
    super.entityInit();
    this.dataManager.register(INITIATED, Boolean.valueOf(false));
  }
  
  protected float getGravityVelocity()
  {
    return 0.005F;
  }
  
  protected abstract float getImpactDamage(Entity paramEntity);
  
  protected boolean isInited()
  {
    return ((Boolean)this.dataManager.get(INITIATED)).booleanValue();
  }
  
  protected boolean onBlockHit(BlockPos blockPos)
  {
    return true;
  }
  
  protected boolean onEntityHit(Entity entity)
  {
    return true;
  }
  
  protected boolean onEntityHit(EntityLivingBase living)
  {
    return onEntityHit(living);
  }
  
  protected void onImpact(RayTraceResult target)
  {
    if (!this.worldObj.isRemote)
    {
      boolean broken;
      if (target.entityHit == null)
      {
        broken = this.worldObj.getBlockState(target.getBlockPos()).getCollisionBoundingBox(this.worldObj, target.getBlockPos()) != null ? onBlockHit(target.getBlockPos()) : false;
      }
      else
      {
        if (((target.entityHit instanceof EntityLivingBase)) && (target.entityHit.equals(getThrower())) && (this.ticksExisted < 5)) {
          return;
        }
        broken = onEntityHit((target.entityHit instanceof EntityLivingBase) ? (EntityLivingBase)target.entityHit : target.entityHit);
      }
      if (broken) {
        setDead();
      }
    }
  }
  
  public void onUpdate()
  {
    if (isInited())
    {
      super.onUpdate();
      onUpdated();
    }
  }
  
  public void onUpdated() {}
  
  public void readEntityFromNBT(NBTTagCompound tagCompund)
  {
    super.readEntityFromNBT(tagCompund);
    this.dataManager.set(INITIATED, Boolean.valueOf(tagCompund.getBoolean("initiated")));
  }
  
  public void setInited()
  {
    this.dataManager.set(INITIATED, Boolean.valueOf(true));
  }
  
  public void writeEntityToNBT(NBTTagCompound tagCompound)
  {
    super.writeEntityToNBT(tagCompound);
    tagCompound.setBoolean("initiated", ((Boolean)this.dataManager.get(INITIATED)).booleanValue());
  }
}
