package clothes;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ClothesMod.MODID, version = ClothesMod.VERSION)
public class ClothesMod
{
    public static final String MODID = "clothesmod";
    public static final String VERSION = "0.0.1.0";
    
    /* All mod items are put within this creative tab. */
    public static CreativeTabs tab;

    /* Store all item instances within mod class. */
    private static Item[] items = {
    };
    
    /* List of block instances. */
    private static Block[] blocks = {
    };
    
    /* Recipes and commands go here. */
    // TODO

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Add creative tab.
        // Needs to happen before items and blocks are initialized!
        tab = new CreativeTabs("More Clothes") {
            @Override
            @SideOnly(Side.CLIENT)
            public Item getTabIconItem() {
                return Items.POTIONITEM;
            }
        };
        
        // Models and textures must be loaded in pre-init!
        registerItems();
        registerBlocks();
        registerTileEntities();
        registerRecipes();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("MORE CLOTHES: Is this going to work?");
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
            b.setCreativeTab(tab);
            ItemBlock ib = new ItemBlock(b);
            ib.setRegistryName(b.getRegistryName());
            ModelLoader.setCustomModelResourceLocation(ib, 0,
                    new ModelResourceLocation(ib.getRegistryName(), "inventory"));
            GameRegistry.register(b);
            GameRegistry.register(ib);
        }
    }
    
    private static void registerTileEntities() {
        // what does this even do?
        //GameRegistry.registerTileEntity(TileEntityTeapot.class, "tile_teapot");
    }

    private static void registerItems() {
        for(Item i : items) {
            i.setCreativeTab(tab);
            ModelLoader.setCustomModelResourceLocation(i, 0, 
                new ModelResourceLocation(i.getRegistryName(), "inventory"));
            GameRegistry.register(i);
        }
    }

    // Only one command.
    private static void registerCommands(FMLServerStartingEvent event) {
        //event.registerServerCommand(new CommandSetTaste());
    }
    
    // Only one recipe for right now.
    private static void registerRecipes() {
        //ModRecipes.register(items[1]); // :TEMP:
        //ItemStack product = new ItemStack(items[1], 5); // 5 Acorns
        //Object[] reactants = new Object[] { new ItemStack(Blocks.SAPLING, 1, 0) };
        //GameRegistry.addShapelessRecipe(product, reactants);
    }
}
