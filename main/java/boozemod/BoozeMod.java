package boozemod;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

//import net.minecraft.block.Block;
//import boozemod.BlockGlowDirt;
//import net.minecraft.block.ItemBlock;

@Mod(modid = BoozeMod.MODID, version = BoozeMod.VERSION)
public class BoozeMod
{
    public static final String MODID = "boozemod";
    public static final String VERSION = "0.0.1.0";

    //public static Block glowDirt;

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // Smoke test.
        System.out.println("BOOZE MOD IS HERE BABY");

        // Basic block testing.
        //glowDirt = Block();
        //glowDirt.setUnlocalizedName("Glow Dirt");
        //glowDirt.setLightLevel(1.0f);
        //glowDirt.setRegistryName("glowdirt");
        //GameRegistry.register(glowDirt);
        // nothing works
    }
}
