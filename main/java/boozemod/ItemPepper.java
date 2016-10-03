package boozemod;

import boozemod.BoozeFood;
import net.minecraft.item.ItemFood;

public class ItemPepper extends BoozeFood {
    public ItemPepper() {
        // BoozeFood(heal, saturation, taste)
        // tastes: 0=nutty 1=spicy 2=sweet other=strange
        super(2, 3.0f, 1);
        this.setUnlocalizedName("Pepper");
        this.setRegistryName("pepper");
    }
}
