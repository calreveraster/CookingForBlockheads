package net.blay09.mods.cookingforblockheads.compat;

import codechicken.nei.api.API;
import codechicken.nei.api.GuiInfo;
import codechicken.nei.api.IConfigureNEI;
import net.blay09.mods.cookingforblockheads.client.GuiRecipeBook;
import net.blay09.mods.cookingforblockheads.container.slot.SlotCraftMatrix;
import net.blay09.mods.cookingforblockheads.container.slot.SlotRecipe;

public class NEICookingForBlockheadsConfig implements IConfigureNEI {

    @Override
    public void loadConfig() {
        GuiInfo.customSlotGuis.add(GuiRecipeBook.class);
        API.addFastTransferExemptSlot(SlotRecipe.class);
        API.addFastTransferExemptSlot(SlotCraftMatrix.class);
        API.registerNEIGuiHandler(NEIRecipeBookGuiHandler.instance);
    }

    @Override
    public String getName() {
        return "Cooking for Blockheads";
    }

    @Override
    public String getVersion() {
        return "1.x.x";
    }
}
