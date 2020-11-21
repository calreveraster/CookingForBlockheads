package net.blay09.mods.cookingforblockheads.addon;

import net.blay09.mods.cookingforblockheads.KitchenMultiBlock;

public class EnviroMineAddon {

    public EnviroMineAddon() {
        KitchenMultiBlock.tileEntityWrappers.put("enviromine.blocks.tiles.TileEntityFreezer", SimpleStorageProvider.class);
    }

}
