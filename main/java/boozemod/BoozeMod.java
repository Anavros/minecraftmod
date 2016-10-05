package boozemod;

import boozemod.commands.CommandSetTaste;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import boozemod.items.*;
import boozemod.blocks.*;

@Mod(modid = BoozeMod.MODID, version = BoozeMod.VERSION)
public class BoozeMod
{
    public static final String MODID = "boozemod";
    public static final String VERSION = "0.0.1.0";

    /* Store all item instances within mod class. */
    private static Item[] items = {
        new ItemMeal(),
        new ItemAcorn(),
        new ItemDrink(),
        new ItemPepper()
    };
    
    /* List of block instances. */
    private static Block[] blocks = {
        new BlockTeapot(),
        new BlockChopping()
    };
    
    /* Recipes and commands go here. */

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Models and textures must be loaded in pre-init!
        registerItems();
        registerBlocks();
        registerRecipes();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("BOOZE MOD IS HERE BABY");
    }

    @EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        registerCommands(event);
    }
    
    // Not sure what's supposed to go where.
    //private static void preInitCommon() { }
    //private static void preInitClient() { }
    //private static void InitCommon() { }
    //private static void InitClient() { }
    //private static void postInitCommon() { }
    //private static void postInitClient() { }
    
    private static void registerBlocks() {
        for(Block b : blocks) {
            ItemBlock ib = new ItemBlock(b);
            ib.setRegistryName(b.getRegistryName());
            GameRegistry.register(b);
            GameRegistry.register(ib);
        }
    }
    
    private static void registerItems() {
        for(Item i : items) {
            ModelLoader.setCustomModelResourceLocation(i, 0, 
                new ModelResourceLocation(i.getRegistryName(), "inventory"));
            GameRegistry.register(i);
        }
    }

    // Only one command.
    private static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSetTaste());
    }
    
    // Only one recipe for right now.
    private static void registerRecipes() {
        //ModRecipes.register(items[1]); // :TEMP:
        ItemStack product = new ItemStack(items[1], 5); // 5 Acorns
        Object[] reactants = new Object[] { new ItemStack(Blocks.SAPLING, 1, 0) };
        GameRegistry.addShapelessRecipe(product, reactants);
    }
}
