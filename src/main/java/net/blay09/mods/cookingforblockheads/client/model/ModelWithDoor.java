package net.blay09.mods.cookingforblockheads.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public abstract class ModelWithDoor extends ModelBase {
    protected boolean isFlipped;
    public ModelRenderer BottomWall;
    public ModelRenderer RightWall;
    public ModelRenderer LeftWall;
    public ModelRenderer TopWall;
    public ModelRenderer BackWall;
    public ModelRenderer Door;
    public ModelRenderer DoorFlipped;
    public ModelRenderer DoorHandle;
    public ModelRenderer DoorHandleFlipped;
    
    public void renderColored() {
        float f5 = 0.0625f;
        if(isFlipped) {
            this.DoorFlipped.render(f5);
        } else {
            this.Door.render(f5);
        }
        this.RightWall.render(f5);
        this.LeftWall.render(f5);
        this.TopWall.render(f5);
        this.BottomWall.render(f5);
        this.BackWall.render(f5);
    }
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public abstract void renderUncolored();
    public abstract void renderInterior();
    
    public void setFlipped(boolean doorFlipped) {
        this.isFlipped = doorFlipped;
    }
}
