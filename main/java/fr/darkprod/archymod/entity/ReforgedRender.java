package fr.darkprod.archymod.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public abstract class ReforgedRender<T extends Entity>
  extends Render<T>
{
  ReforgedModel model;
  float scale = 1.0F;
  int modifier = 0;
  
  protected ReforgedRender(RenderManager renderManager, ReforgedModel model, float scale, int rotationModifier)
  {
    this(renderManager, model, rotationModifier);
  }
  
  protected ReforgedRender(RenderManager renderManager, ReforgedModel model, int rotationModifier)
  {
    super(renderManager);
    this.model = model;
    this.modifier = rotationModifier;
  }
  
  public void doRender(T bullet, double x, double y, double z, float yaw, float partialTicks)
  {
    super.doRender(bullet, x, y, z, yaw, partialTicks);
    renderEntityModel(bullet, x, y, z, yaw, partialTicks);
  }
  
  protected abstract ResourceLocation getEntityTexture(T paramT);
  
  public void renderEntityModel(T theEntity, double x, double y, double z, float yaw, float partialTicks)
  {
    GL11.glPushMatrix();
    bindTexture(getEntityTexture(theEntity));
    GL11.glTranslated(x, y, z);
    GL11.glScalef(this.scale, this.scale, this.scale);
    GL11.glRotated(theEntity.prevRotationYaw + (theEntity.rotationYaw - theEntity.prevRotationYaw) * partialTicks - 90.0F, 0.0D, 1.0D, 0.0D);
    
    GL11.glRotated(theEntity.prevRotationPitch + (theEntity.rotationPitch - theEntity.prevRotationPitch) * partialTicks, 0.0D, 0.0D, 1.0D);
    
    this.model.render(theEntity, (float)x, (float)y, (float)z, yaw + this.modifier, partialTicks, 0.0475F);
    GL11.glPopMatrix();
  }
}
