package net.blay09.mods.cookingforblockheads.compat;

import java.util.ArrayList;
import java.util.List;

import net.blay09.mods.cookingforblockheads.KitchenMultiBlock;
import net.blay09.mods.cookingforblockheads.api.CookingForBlockheadsAPI;
import net.blay09.mods.cookingforblockheads.api.ToastHandler;
import net.blay09.mods.cookingforblockheads.api.event.FoodRegistryInitEvent;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenItemProvider;
import net.blay09.mods.cookingforblockheads.api.kitchen.IKitchenSmeltingProvider;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;

//quick and (extremely) dirty test. Pulled the Harvestcraft addon code and adapted it to make the CFM blender stand-in as the juicer from harvestcraft.
//still does not test to ensure CFM is even loaded, so even if this builds, it's far from "ready for action". This is just a small attempt to make the 
//multiblock kitchen as interesting as possible by exploring ideas for standin blocks with respect to recipes. 

public class CFMAddon {

  public abstract static class ToolWrapper implements IKitchenItemProvider 
  {
      protected final List<ItemStack> itemStacks = new ArrayList<>();
      public ToolWrapper(Block block) {}

      @Override
      public List<ItemStack> getProvidedItemStacks() 
      {
          return itemStacks;
      }

      @Override
      public boolean addToCraftingBuffer(ItemStack itemStack) 
      {
          return true;
      }

      @Override
      public void clearCraftingBuffer() {}

      @Override
      public void craftingComplete() {}
  }

  public static class BlenderWrapper extends ToolWrapper 
  {
      public BlenderWrapper(Block block) 
      {
          super(block);
          itemStacks.add(GameRegistry.findItemStack("harvestcraft", "juicerItem", 1));
      }
  }

/*
    private static final String[] ADDITIONAL_RECIPES = new String[] { "flourItem", "doughItem", "cornmealItem",
            "freshwaterItem", "pastaItem", "vanillaItem", "butterItem", "heavycreamItem", "saltItem", "freshmilkItem",
            "mayoItem", "cocoapowderItem", "ketchupItem", "vinegarItem", "mustardItem", "blackpepperItem",
            "groundcinnamonItem", "groundnutmegItem", "saladdressingItem", "batterItem", "oliveoilItem",
            "carrotcakeItem", "cheesecakeItem", "cherrycheesecakeItem", "pineappleupsidedowncakeItem",
            "chocolatesprinklecakeItem", "redvelvetcakeItem", "lamingtonItem", "pavlovaItem", "holidaycakeItem",
            "pumpkincheesecakeItem",
            // "toastedsesameseedsItem", // no usage
            "bubblywaterItem", "currypowderItem", "soysauceItem", "garammasalaItem", };

    private static final String[] OVEN_RECIPES = new String[] { "turkeyrawItem", "turkeycookedItem", "rabbitrawItem",
            "rabbitcookedItem", "venisonrawItem", "venisoncookedItem" };

    private static final String[] TOOLS = new String[] { "cuttingboardItem", "potItem", "skilletItem", "saucepanItem",
            "bakewareItem", "mortarandpestleItem", "mixingbowlItem", "juicerItem" };

    private static final String TOAST_ITEM = "toastItem";
*/
  public CFMAddon() 
  {
       // KitchenMultiBlock.tileEntityWrappers.put("com.pam.harvestcraft.TileEntityOven", OvenWrapper.class);
        KitchenMultiBlock.blockWrappers.put("cfm:blender", BlenderWrapper.class);

       /* for (int i = 0; i < OVEN_RECIPES.length; i += 2) {
            ItemStack source = GameRegistry.findItemStack("harvestcraft", OVEN_RECIPES[i], 1);
            ItemStack result = GameRegistry.findItemStack("harvestcraft", OVEN_RECIPES[i + 1], 1);
            if (source != null && result != null) {
                CookingForBlockheadsAPI.addOvenRecipe(source, result);
            }
       }*/ 

        for (String toolName : TOOLS) 
        {
            ItemStack toolItem = GameRegistry.findItemStack("cfm", toolName, 1);
            if (toolItem != null) 
            {
                CookingForBlockheadsAPI.addToolItem(toolItem);
            }
        }
    }
}
