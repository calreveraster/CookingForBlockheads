package net.blay09.mods.cookingforblockheads.addon;

import cpw.mods.fml.common.Optional;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.util.IEventHandler;
import net.blay09.mods.cookingforblockheads.registry.CookingRegistry;

@Optional.Interface(modid = "MineTweaker3", iface = "minetweaker.util.IEventHandler", striprefs = true)
public class MineTweakerAddon implements IEventHandler<MineTweakerImplementationAPI.ReloadEvent> {

    public MineTweakerAddon() {
        MineTweakerImplementationAPI.onPostReload(this);
    }

    @Override
    public void handle(MineTweakerImplementationAPI.ReloadEvent reloadEvent) {
        CookingRegistry.initFoodRegistry();
    }
}
