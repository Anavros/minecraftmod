package boozemod.items;

import java.util.List;
import boozemod.BoozeFood;
//import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
//import net.minecraft.client.renderer.color.IItemColor;

public class ItemPepper extends BoozeFood {
    public ItemPepper() {
        super();
        this.setUnlocalizedName("Pepper");
        this.setRegistryName("pepper");
    }

    //@Override
    // Adds a color multiplier on the ItemStack's texture.
    //public int getColorFromItemstack(ItemStack stack, int layer) {
        // assume layer 0 for now
        //int c;
        //int[] flavor = BoozeFood.getFlavor(stack);
        //switch(flavor[0]) {
            //case 0: c = Color.BROWN.getRGB(); break;
            //case 1: c = Color.RED.getRGB(); break;
            //case 2: c = Color.BLUE.getRGB(); break;
            //default: c = Color.BLACK.getRGB(); break;
        //}
        //return c;
    //}
}
