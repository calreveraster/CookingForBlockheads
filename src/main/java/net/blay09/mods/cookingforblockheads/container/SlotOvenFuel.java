package net.blay09.mods.cookingforblockheads.container;

import net.blay09.mods.cookingforblockheads.block.TileEntityCookingOven;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOvenFuel extends Slot {

    public SlotOvenFuel(IInventory inventory, int i, int x, int y) {
        super(inventory, i, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return TileEntityCookingOven.isItemFuel(itemStack);
    }

}
