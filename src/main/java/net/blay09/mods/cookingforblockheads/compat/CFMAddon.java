package net.blay09.mods.cookingforblockheads.compat;

import java.util.ArrayList;
import java.util.List;

import net.blay09.mods.cookingforblockheads.CookingConfig;
import net.blay09.mods.cookingforblockheads.KitchenMultiBlock;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenItemProvider;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenStorageProvider;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

// quick and (extremely) dirty test. Pulled the Harvestcraft addon code and adapted it to make the CFM blender stand-in
// as the juicer from harvestcraft.
// still does not test to ensure CFM is even loaded, so even if this builds, it's far from "ready for action". This is
// just a small attempt to make the
// multiblock kitchen as interesting as possible by exploring ideas for standin blocks with respect to recipes.
// Also, this last line is just me testing to make sure I push correctly to GH.

public class CFMAddon {

    public abstract static class ToolWrapper implements IKitchenItemProvider {

        protected final List<ItemStack> itemStacks = new ArrayList<>();

        public ToolWrapper(Block block) {}

        @Override
        public List<ItemStack> getProvidedItemStacks() {
            return itemStacks;
        }

        @Override
        public boolean addToCraftingBuffer(ItemStack itemStack) {
            return true;
        }

        @Override
        public void clearCraftingBuffer() {}

        @Override
        public void craftingComplete() {}
    }

    public static class BlenderWrapper extends ToolWrapper {

        public BlenderWrapper(Block block) {
            super(block);

            // if harvestcraft is also loaded, we're going to permit the blender to stand in for the juicer
            if (CookingConfig.moduleCFM == true) {
                itemStacks.add(GameRegistry.findItemStack("harvestcraft", "juicerItem", 1));
            }

            // I need to figure out a good way to trigger the blender if used from the cooking table...
        }
    }

    // CFM's kitchen cabinet should also work as a valid cabinet
    public static class CabinetWrapper implements IKitchenStorageProvider {

        TileEntity tile;

        public CabinetWrapper(TileEntity tile) {
            this.tile = tile;
        }

        @Override
        public IInventory getInventory() {
            return (IInventory) tile;
        }
    }

    public CFMAddon() {
        KitchenMultiBlock.blockWrappers.put("cfm:blender", BlenderWrapper.class);
        KitchenMultiBlock.blockWrappers.put("cfm:kitchencabinet", CabinetWrapper.class);
    }
}
