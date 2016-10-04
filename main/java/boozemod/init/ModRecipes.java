package boozemod.init;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import boozemod.items.ItemAcorn;

public class ModRecipes {
    public static void register(Item item) {
        GameRegistry.addShapelessRecipe(new ItemStack(item, 5), new Object[]{new ItemStack(Blocks.SAPLING, 1, 0)});
    }
}
