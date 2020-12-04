package net.blay09.mods.cookingforblockheads;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.blay09.mods.cookingforblockheads.compat.VanillaAddon;
import net.blay09.mods.cookingforblockheads.item.ItemBlockFridge;
import net.blay09.mods.cookingforblockheads.item.ItemBlockGenericKitchen;
import net.blay09.mods.cookingforblockheads.network.NetworkHandler;
import net.blay09.mods.cookingforblockheads.registry.CookingRegistry;
import net.blay09.mods.cookingforblockheads.tile.TileCabinet;
import net.blay09.mods.cookingforblockheads.tile.TileCookingTable;
import net.blay09.mods.cookingforblockheads.tile.TileCounter;
import net.blay09.mods.cookingforblockheads.tile.TileFridge;
import net.blay09.mods.cookingforblockheads.tile.TileOven;
import net.blay09.mods.cookingforblockheads.tile.TileSink;
import net.blay09.mods.cookingforblockheads.tile.TileToaster;
import net.blay09.mods.cookingforblockheads.tile.TileToolRack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy {

	private boolean mineTweakerHasPostReload;

	public void preInit(FMLPreInitializationEvent event) {}

	public void init(FMLInitializationEvent event) {
		GameRegistry.registerItem(CookingForBlockheads.itemRecipeBook, "recipebook");
		GameRegistry.registerItem(CookingForBlockheads.itemToast, "toast");
		GameRegistry.registerBlock(CookingForBlockheads.blockCookingTable, ItemBlockGenericKitchen.class, "cookingtable", new Object[]{"cookingtable", false});
		GameRegistry.registerBlock(CookingForBlockheads.blockOven, ItemBlockGenericKitchen.class, "cookingoven", new Object[]{"cookingoven", false});
		GameRegistry.registerBlock(CookingForBlockheads.blockCounter, ItemBlockGenericKitchen.class, "counter", new Object[]{"counter", true});
		GameRegistry.registerBlock(CookingForBlockheads.blockCabinet, ItemBlockGenericKitchen.class, "cabinet", new Object[]{"cabinet", true});
		GameRegistry.registerBlock(CookingForBlockheads.blockFridge, ItemBlockFridge.class, "fridge");
		GameRegistry.registerBlock(CookingForBlockheads.blockKitchenFloor, ItemBlockGenericKitchen.class, "kitchen_floor", new Object[]{"kitchen_floor", false});
		GameRegistry.registerBlock(CookingForBlockheads.blockSink, ItemBlockGenericKitchen.class, "sink", new Object[]{"sink", false});
		GameRegistry.registerBlock(CookingForBlockheads.blockToolRack, ItemBlockGenericKitchen.class, "toolrack", new Object[]{"toolrack", false});
		GameRegistry.registerBlock(CookingForBlockheads.blockToaster, ItemBlockGenericKitchen.class, "toaster", new Object[]{"toaster", false});
		
		GameRegistry.registerTileEntity(TileOven.class, CookingForBlockheads.MOD_ID + ":cookingoven");
		GameRegistry.registerTileEntity(TileFridge.class, CookingForBlockheads.MOD_ID + ":fridge");
		GameRegistry.registerTileEntity(TileCounter.class, CookingForBlockheads.MOD_ID + ":counter");
		GameRegistry.registerTileEntity(TileCabinet.class, CookingForBlockheads.MOD_ID + ":cabinet");
		GameRegistry.registerTileEntity(TileToolRack.class, CookingForBlockheads.MOD_ID + ":toolrack");
		GameRegistry.registerTileEntity(TileSink.class, CookingForBlockheads.MOD_ID + ":sink");
		GameRegistry.registerTileEntity(TileCookingTable.class, CookingForBlockheads.MOD_ID + ":cookingtable");
		GameRegistry.registerTileEntity(TileToaster.class, CookingForBlockheads.MOD_ID + ":toaster");

		// #NoFilter Edition
		if(CookingConfig.enableNoFilter) {
			GameRegistry.addShapelessRecipe(new ItemStack(CookingForBlockheads.itemRecipeBook, 1, 3), Items.book, Items.painting);
		}

		// Cooking for Blockheads I
		GameRegistry.addSmelting(Items.book, new ItemStack(CookingForBlockheads.itemRecipeBook), 0f);
		if (CookingConfig.enableNoFilter) {
			GameRegistry.addSmelting(new ItemStack(CookingForBlockheads.itemRecipeBook, 1, 3), new ItemStack(CookingForBlockheads.itemRecipeBook), 0f);
		}

		// Cooking for Blockheads II
		if(CookingConfig.enableCraftingBook) {
			GameRegistry.addRecipe(new ItemStack(CookingForBlockheads.itemRecipeBook, 1, 1), " C ", "DBD", " C ", 'C', Blocks.crafting_table, 'D', Items.diamond, 'B', CookingForBlockheads.itemRecipeBook);
		}

		// Fridge
		GameRegistry.addShapelessRecipe(new ItemStack(CookingForBlockheads.blockFridge), Blocks.chest, Items.iron_door);

		// Sink
		if(CookingConfig.enableSink) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CookingForBlockheads.blockSink), "III", "WBW", "WWW", 'I', "ingotIron", 'W', "logWood", 'B', Items.water_bucket));
		}

		// Toaster
		if(CookingConfig.enableToaster) {
			// TODO Toaster Recipe
			//GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CookingForBlockheads.blockSink), "III", "WBW", "WWW", 'I', "ingotIron", 'W', "logWood", 'B', Items.water_bucket));
		}

		// Cooking Table
		if(CookingConfig.enableCraftingBook) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CookingForBlockheads.blockCookingTable), "CCC", "WBW", "WWW", 'B', new ItemStack(CookingForBlockheads.itemRecipeBook, 1, 1), 'W', "logWood", 'C', new ItemStack(Blocks.stained_hardened_clay, 1, 15)));
		}

		// Cooking Oven
		if(CookingConfig.enableOven) {
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CookingForBlockheads.blockOven), "GGG", "IFI", "III", 'G', new ItemStack(Blocks.stained_glass, 1, 15), 'I', "ingotIron", 'F', Blocks.furnace));
		}

		// Tool Rack
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(CookingForBlockheads.blockToolRack), "PPP", "I I", 'P', Blocks.wooden_pressure_plate, 'I', "ingotIron"));

		NetworkHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(CookingForBlockheads.instance, new GuiHandler());
	}

	public void postInit(FMLPostInitializationEvent event) {
		if(CookingConfig.moduleVanilla) {
			new VanillaAddon();
		}

		try {
			Class<?> mtClass = Class.forName("minetweaker.MineTweakerImplementationAPI");
			mtClass.getMethod("onPostReload", Class.forName("minetweaker.util.IEventHandler"));
			event.buildSoftDependProxy("MineTweaker3", "net.blay09.mods.cookingforblockheads.compat.MineTweakerAddon");
			mineTweakerHasPostReload = true;
		} catch (ClassNotFoundException | NoSuchMethodException ignored) {}

		if(CookingConfig.moduleHarvestCraft) {
			event.buildSoftDependProxy("harvestcraft", "net.blay09.mods.cookingforblockheads.compat.HarvestCraftAddon");
		}
		if(CookingConfig.moduleAppleCore) {
			event.buildSoftDependProxy("AppleCore", "net.blay09.mods.cookingforblockheads.compat.AppleCoreAddon");
		}
		if(CookingConfig.moduleStorageDrawers) {
			event.buildSoftDependProxy("StorageDrawers", "net.blay09.mods.cookingforblockheads.compat.StorageDrawersAddon");
		}
 
		CookingRegistry.initFoodRegistry();
	}

	public void serverStarted(FMLServerStartedEvent event) {
		if(!mineTweakerHasPostReload && Loader.isModLoaded("MineTweaker3")) {
			CookingRegistry.initFoodRegistry();
		}
	}
}
