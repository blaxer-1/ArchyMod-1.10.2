package fr.darkprod.archymod.entity;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderDynamite
  extends ReforgedRender<EntityDynamite>
{
  public RenderDynamite(RenderManager renderManager)
  {
    super(renderManager, new ModelDynamite(), 0);
  }
  
  protected ResourceLocation getEntityTexture(EntityDynamite entity)
  {
    return new ResourceLocation("archydium", "textures/entity/dynamite.png");
  }
}
