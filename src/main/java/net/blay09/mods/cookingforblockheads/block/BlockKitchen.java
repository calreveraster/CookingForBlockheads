package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.CookingForBlockheads;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BlockKitchen extends BlockContainer {

    protected BlockKitchen(Material material) {
        super(material);
        setCreativeTab(CookingForBlockheads.creativeTab);
    }
//
//    @Override
//    public boolean recolorBlock(World world, BlockPos pos, EnumFacing side, EnumDyeColor color) {
//        TileEntity tileEntity = world.getTileEntity(pos);
//        if (tileEntity instanceof IDyeableKitchen) {
//            IDyeableKitchen dyeable = (IDyeableKitchen) tileEntity;
//            if (dyeable.getDyedColor() == color) {
//                return false;
//            }
//
//            dyeable.setDyedColor(color);
//        }
//
//        return true;
//    }
}