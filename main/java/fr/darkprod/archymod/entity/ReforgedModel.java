package fr.darkprod.archymod.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ReforgedModel
  extends ModelBase
{
  public ReforgedModel()
  {
    this.textureWidth = 64;
    this.textureHeight = 32;
  }
  
  public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
  {
    modelRenderer.rotateAngleX = x;
    modelRenderer.rotateAngleY = y;
    modelRenderer.rotateAngleZ = z;
  }
}
