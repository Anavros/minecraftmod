package boozemod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import boozemod.blocks.BlockChopping;
import boozemod.blocks.BlockTeapot;
import boozemod.init.ModCommands;
import boozemod.init.ModRecipes;
import boozemod.items.ItemAcorn;
import boozemod.items.ItemDrink;
import boozemod.items.ItemMeal;
import boozemod.items.ItemPepper;

@Mod(modid = BoozeMod.MODID, version = BoozeMod.VERSION)
public class BoozeMod
{
    public static final String MODID = "boozemod";
    public static final String VERSION = "0.0.1.0";

    // does this have to be outside the init definition?
    private static ItemAcorn acorn;
    private static ItemPepper pepper;
    private static ItemMeal meal;
    private static ItemDrink drink;
    private static BlockTeapot teapot;
    private static ItemBlock teapotItemBlock;
    private static BlockChopping chopper;
    private static ItemBlock chopperItemBlock;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Models and textures must be loaded in pre-init!
        // instantiate the item
        acorn = new ItemAcorn();
        // submit the model
        ModelLoader.setCustomModelResourceLocation(acorn, 0, new ModelResourceLocation(
            "boozemod:acorn", "inventory"));
        // and register
        GameRegistry.register(acorn);

        pepper = new ItemPepper();
        ModelLoader.setCustomModelResourceLocation(pepper, 0, new ModelResourceLocation(
            "boozemod:pepper", "inventory"));
        GameRegistry.register(pepper);

        meal = new ItemMeal();
        ModelLoader.setCustomModelResourceLocation(meal, 0, new ModelResourceLocation(
            "boozemod:meal", "inventory"));
        GameRegistry.register(meal);

        drink = new ItemDrink();
        ModelLoader.setCustomModelResourceLocation(drink, 0, new ModelResourceLocation(
            "boozemod:drink", "inventory"));
        GameRegistry.register(drink);

        // init blocks
        teapot = new BlockTeapot();
        teapotItemBlock = new ItemBlock(teapot);
        GameRegistry.register(teapot);
        GameRegistry.register(teapotItemBlock.setRegistryName(teapot.getRegistryName()));

        chopper = new BlockChopping();
        chopperItemBlock = new ItemBlock(chopper);
        GameRegistry.register(chopper);
        GameRegistry.register(chopperItemBlock.setRegistryName(chopper.getRegistryName()));

        // init recipies
        ModRecipes.register(acorn);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("BOOZE MOD IS HERE BABY");
    }

    @EventHandler
    public void onServerLoad(FMLServerStartingEvent event) {
        ModCommands.registerCommands(event);
    }
}
