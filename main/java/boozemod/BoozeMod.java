package boozemod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@Mod(modid = BoozeMod.MODID, version = BoozeMod.VERSION)
public class BoozeMod
{
    public static final String MODID = "boozemod";
    public static final String VERSION = "0.0.1.0";

    // does this have to be outside the init definition?
    public static Item acorn;
    public static Block teapot;
    public static ItemBlock teapotItem;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("BOOZE MOD IS HERE BABY");

        // Basic testing.
        // Add a simple item, an acorn.
        acorn = new Item(); // make sure you type "new Item()" and not just "Item()"!
        acorn.setUnlocalizedName("Acorn");
        GameRegistry.register(acorn.setRegistryName("acorn"));
        // Now add a block, a teapot.
        // The Block instantiator requires a Material.
        // Material is an enum as far as I can tell.
        teapot = new Block(Material.IRON).setUnlocalizedName("Teapot");
        GameRegistry.register(teapot.setRegistryName("teapot"));
        // The block also needs an associated ItemBlock.
        // So create a *new* ItemBlock using the block variable as an argument.
        teapotItem = new ItemBlock(teapot);
        GameRegistry.register(teapotItem.setRegistryName("teapot"));
    }
}
