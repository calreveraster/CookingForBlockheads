package net.blay09.mods.cookingforblockheads.block;

import net.blay09.mods.cookingforblockheads.client.render.block.CabinetCornerBlockRenderer;
import net.blay09.mods.cookingforblockheads.tile.TileCabinetCorner;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockCabinetCorner extends BlockBaseKitchen {

    public BlockCabinetCorner() {
        super(Material.iron);
        setBlockName("cookingforblockheads:cabinet_corner");
        setStepSound(soundTypeStone);
        setHardness(5f);
        setResistance(10f);
    }

    @Override
    public IIcon getIcon(int side, int metadata) {
        return Blocks.stone.getIcon(side, 0);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemStack) {
        double blockRotation = (double) (placer.rotationYaw * 4.0F / 360.0F) + 0.5D;
        int orientation = MathHelper.floor_double(blockRotation) & 3;
        if (orientation == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (orientation == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (orientation == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (orientation == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
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

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(player.getHeldItem() != null && player.getHeldItem().getItem() == Items.dye) {
            int dye = BlockColored.func_150032_b(player.getHeldItem().getItemDamage());
            TileCabinetCorner corner = (TileCabinetCorner) world.getTileEntity(x, y, z);
            corner.setColor(dye);
            player.getHeldItem().stackSize--;
            return true;
        }
        return true;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z) {
        return super.canPlaceBlockAt(world, x, y, z);
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        super.breakBlock(world, x, y, z, block, metadata);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        super.onNeighborBlockChange(world, x, y, z, block);
    }
}
