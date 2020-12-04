package net.blay09.mods.cookingforblockheads.tile;

import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenStorageProvider;
import net.minecraft.inventory.IInventory;

public class TileCabinet extends TileCounter implements IInventory, IKitchenStorageProvider {
    protected static final String name = "cabinet";


    @Override
    public boolean receiveClientEvent(int id, int value) {
        return super.receiveClientEvent(id, value);
    }

}