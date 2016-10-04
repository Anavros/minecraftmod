package boozemod.items;

import java.util.List;
import boozemod.BoozeFood;
//import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
//import net.minecraft.client.renderer.color.IItemColor;
//import net.minecraft.client.renderer.color.ItemColors; // ???

public class ItemPepper extends BoozeFood { //implements IItemColor {
    public ItemPepper() {
        super();
        this.setUnlocalizedName("Pepper");
        this.setRegistryName("pepper");
    }

    // Adds a color multiplier on the ItemStack's texture.
    // This just straight-up doesn't work.
    // There's some over-complicated model manager way to do it now.
    //@Override
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
        //return 0xFF0000;
    //}
}
