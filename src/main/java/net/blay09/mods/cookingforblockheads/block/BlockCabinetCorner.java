package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.client.render.block.CabinetCornerBlockRenderer;
import net.blay09.mods.cookingforblockheads.tile.TileCabinetCorner;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCabinetCorner extends BlockCounterCorner {

    public BlockCabinetCorner() {
        setBlockName("cookingforblockheads:cabinet_corner");
        setStepSound(soundTypeStone);
        setHardness(5f);
        setResistance(10f);
    }

    @Override
    public int getRenderType() {
        return CabinetCornerBlockRenderer.RENDER_ID;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCabinetCorner();
    }

    @Override
    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour) {
        TileCabinetCorner corner = (TileCabinetCorner) world.getTileEntity(x, y, z);
        corner.setColor(colour);
        return true;
    }

}
