package net.blay09.mods.cookingforblockheads.compat;

import java.util.ArrayList;
import java.util.List;

import net.blay09.mods.cookingforblockheads.CookingConfig;
import net.blay09.mods.cookingforblockheads.KitchenMultiBlock;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenItemProvider;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenStorageProvider;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.registry.GameRegistry;

// quick and (extremely) dirty test. Pulled the Harvestcraft addon code and adapted it to make the CFM blender stand-in
// as the juicer from harvestcraft.
// still does not test to ensure CFM is even loaded, so even if this builds, it's far from "ready for action". This is
// just a small attempt to make the
// multiblock kitchen as interesting as possible by exploring ideas for standin blocks with respect to recipes.
// Also, this last line is just me testing to make sure I push correctly to GH.

public class CFMAddon {

    // first, create the wrapper that allows us to take a tile and register that the item
    // equivalent exists in the cooking table.
    public abstract static class ToolWrapper implements IKitchenItemProvider {

        protected final List<ItemStack> itemStacks = new ArrayList<>();

        public ToolWrapper(TileEntity tile) {}

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

    // Add blender compat. We want this to do two things. First, stand in as the juicer from HC if HC is installed.
    // Second, we want it to work like an oven, allowing inputs and will hold an output until interacted with.
    public static class BlenderWrapper extends ToolWrapper {

        public BlenderWrapper(TileEntity tile) {
            super(tile);

            // if harvestcraft is also loaded, we're going to permit the blender to stand in for the juicer
            if (CookingConfig.moduleHarvestCraft) {
                itemStacks.add(GameRegistry.findItemStack("harvestcraft", "juicerItem", 1));
            }

            // I need to figure out a good way to trigger the blender if used from the cooking table.
        }
    }

    // Im pretty sure these need to be tile entities, not blocks...
    // adding both for now until I can figure out which one I need.
    public CFMAddon() {
        KitchenMultiBlock.tileEntityWrappers
                .put("com.mrcrayfish.furniture.tileentity.TileEntityBlender", BlenderWrapper.class);
        KitchenMultiBlock.tileEntityWrappers
                .put("com.mrcrayfish.furniture.tileentity.TileEntityCabinetKitchen", CabinetWrapper.class);

        MinecraftForge.EVENT_BUS.register(this);
        // yeet
    }
}
