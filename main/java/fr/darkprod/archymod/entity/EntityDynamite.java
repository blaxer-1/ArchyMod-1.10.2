package fr.darkprod.archymod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class EntityDynamite
  extends AReforgedThrowable
{
  public EntityDynamite(World worldIn)
  {
    super(worldIn, "dynamite");
  }
  
  public EntityDynamite(World worldIn, EntityLivingBase throwerIn, ItemStack stack)
  {
    super(worldIn, throwerIn, stack, "dynamite");
    setInited();
  }
  
  public void explodeDamage(Explosion e, Entity exploder, int size, double x, double y, double z)
  {
    float f3 = size * 2.0F;
    int j = MathHelper.floor_double(x - f3 - 1.0D);
    int k = MathHelper.floor_double(x + f3 + 1.0D);
    int j1 = MathHelper.floor_double(y - f3 - 1.0D);
    int l = MathHelper.floor_double(y + f3 + 1.0D);
    int k1 = MathHelper.floor_double(z - f3 - 1.0D);
    int i1 = MathHelper.floor_double(z + f3 + 1.0D);
    Vec3d vec3 = new Vec3d(x, y, z);
    Entity entity = exploder;
    if (!entity.isImmuneToExplosions())
    {
      double d12 = entity.getDistance(x, y, z) / f3;
      if (d12 <= 1.0D)
      {
        double d5 = entity.posX - x;
        double d7 = entity.posY + entity.getEyeHeight() - y;
        double d9 = entity.posZ - z;
        double d13 = MathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);
        if (d13 != 0.0D)
        {
          d5 /= d13;
          d7 /= d13;
          d9 /= d13;
          double d14 = this.worldObj.getBlockDensity(vec3, entity.getEntityBoundingBox());
          double d10 = (1.0D - d12) * d14;
          entity.attackEntityFrom(setExplosionSource(e), (int)((d10 * d10 + d10) / 2.0D * 8.0D * f3 + 1.0D));
        }
      }
    }
  }
  
  protected float getGravityVelocity()
  {
    return 0.03F;
  }
  
  protected float getImpactDamage(Entity target)
  {
    return 0.0F;
  }
  
  protected boolean onBlockHit(BlockPos blockPos)
  {
    this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true);
    return true;
  }
  
  protected boolean onEntityHit(Entity entity)
  {
    this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true);
    return true;
  }
  
  public DamageSource setExplosionSource(Explosion explosionIn)
  {
    return (explosionIn != null) && (explosionIn.getExplosivePlacedBy() != null) ? new EntityDamageSource("explosion.player", explosionIn.getExplosivePlacedBy()).setDifficultyScaled().setExplosion() : new DamageSource("explosion").setDifficultyScaled().setExplosion();
  }
}
