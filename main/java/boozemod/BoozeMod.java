package boozemod;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import boozemod.ItemAcorn;
import boozemod.ItemPepper;
import boozemod.ItemMeal; 
import boozemod.BlockTeapot;
import boozemod.ModEvents;

@Mod(modid = BoozeMod.MODID, version = BoozeMod.VERSION)
public class BoozeMod
{
    public static final String MODID = "boozemod";
    public static final String VERSION = "0.0.1.0";

    // does this have to be outside the init definition?
    private static ItemAcorn acorn;
    private static ItemPepper pepper;
    private static ItemMeal meal;
    private static BlockTeapot teapot;
    private static ItemBlock teapotItemBlock;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("BOOZE MOD IS HERE BABY");

        acorn = new ItemAcorn();
        pepper = new ItemPepper();
        teapot = new BlockTeapot();
        int taste;
        taste = ItemMeal.getRandomTaste();
        meal = new ItemMeal(taste); // can't do that in constructor?
        teapotItemBlock = new ItemBlock(teapot);
        GameRegistry.register(acorn);
        GameRegistry.register(pepper);
        GameRegistry.register(meal);
        GameRegistry.register(teapot);
        GameRegistry.register(teapotItemBlock.setRegistryName(teapot.getRegistryName()));

        // Add mod events, including tooltip handler.
        ModEvents.registerEvents();
    }
}
