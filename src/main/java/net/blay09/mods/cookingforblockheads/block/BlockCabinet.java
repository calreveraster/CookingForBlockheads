package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.client.render.block.CabinetBlockRenderer;
import net.blay09.mods.cookingforblockheads.tile.TileCabinet;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCabinet extends BlockCounter {

    public BlockCabinet() {
        super();
        setBlockName("cookingforblockheads:cabinet");
        setStepSound(soundTypeStone);
        setHardness(5f);
        setResistance(10f);
//        setBlockBounds(0.0625f, 0f, 0.0625f, 0.9375f, 0.975f, 0.9375f);
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return Blocks.stone.getIcon(side, 0);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return true;
    }

    @Override
    public int getRenderType() {
        return CabinetBlockRenderer.RENDER_ID;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int metadata) {
        return new TileCabinet();
    }


}
