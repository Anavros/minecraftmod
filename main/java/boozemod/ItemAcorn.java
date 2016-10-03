package boozemod;

import boozemod.BoozeFood;
import net.minecraft.item.ItemFood;

public class ItemAcorn extends BoozeFood {
    public ItemAcorn() {
        // BoozeFood(heal, saturation, taste)
        // tastes: 0=nutty 1=spicy 2=sweet other=strange
        super(2, 1.5f, 0);
        this.setUnlocalizedName("Acorn");
        this.setRegistryName("acorn");
    }
}
